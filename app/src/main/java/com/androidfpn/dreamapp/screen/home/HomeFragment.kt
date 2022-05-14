package com.androidfpn.dreamapp.screen.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.androidfpn.dreamapp.MyApplication
import com.androidfpn.dreamapp.R
import com.androidfpn.dreamapp.data.locale.entity.Sound
import com.androidfpn.dreamapp.databinding.HomeFragmentBinding
import com.androidfpn.dreamapp.screen.home.adapter.HomeAdapter
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var bestSoundAdapter: HomeAdapter
    private lateinit var newSoundAdapter: HomeAdapter
    private lateinit var _binding: HomeFragmentBinding
    private val binding get() = _binding

    private fun adapterOnClick(sound: Sound) {
        TODO("Not yet implemented")
    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory((requireActivity().application as MyApplication).soundRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapters()
        initializeCategories()
        initializeBestSounds()
        initializeSuggestedSounds()
    }

    private fun initializeAdapters() {
        val bestSoundRecyclerView: RecyclerView = binding.bestSoundsInclude.recycler
        binding.bestSoundsInclude.bestTv.text = getString(R.string.best_text)
        val newSoundRecyclerView: RecyclerView = binding.suggestedSoundInclude.recycler
        binding.suggestedSoundInclude.bestTv.text = getString(R.string.news_text)
        bestSoundAdapter = HomeAdapter { sound -> adapterOnClick(sound) }
        newSoundAdapter = HomeAdapter { sound -> adapterOnClick(sound) }
        bestSoundRecyclerView.adapter = bestSoundAdapter
        newSoundRecyclerView.adapter = newSoundAdapter
    }

    private fun initializeCategories() {
        viewModel.chipsData.observe(viewLifecycleOwner) { items ->
            items.forEach { chipsText ->
                binding.chipGroup.addView(createTagChip(requireContext(), chipsText.name))
            }
        }
    }

    private fun createTagChip(context: Context, chipName: String): Chip {
        return Chip(context).apply {
            text = chipName
            setChipBackgroundColorResource(R.color.indigo_500)
            isCloseIconVisible = false
            id = ViewCompat.generateViewId()
            setTextAppearance(R.style.chipText)

        }
    }

    private fun initializeBestSounds() {
        viewModel.bestSoundsList.observe(viewLifecycleOwner) { sounds ->
            bestSoundAdapter.submitList(sounds)
        }
    }

    private fun initializeSuggestedSounds() {
        viewModel.suggestedSoundsList.observe(viewLifecycleOwner) { sounds ->
            newSoundAdapter.submitList(sounds)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}