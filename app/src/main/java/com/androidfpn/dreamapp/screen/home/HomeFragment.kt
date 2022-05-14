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
import kotlinx.android.synthetic.main.home_fragment.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding


    private fun adapterOnClick(sound: Sound) {
        TODO("Not yet implemented")
    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory(
            (requireActivity().application as MyApplication),
            (requireActivity().application as MyApplication).soundRepository
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding?.homeRecycler?.recycler!!
        val homeAdapter = HomeAdapter { sound -> adapterOnClick(sound) }

        recyclerView.adapter = homeAdapter
        viewModel.chipsData.observe(viewLifecycleOwner) { items ->
            items.forEach { chipsText ->
                binding?.chipGroup?.addView(createTagChip(requireContext(), chipsText.name))
            }
        }

        viewModel.bestSoundsList.observe(viewLifecycleOwner) { items ->
            items.forEach { sounds ->
                homeAdapter.submitList(sounds as MutableList<Sound>)
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


}