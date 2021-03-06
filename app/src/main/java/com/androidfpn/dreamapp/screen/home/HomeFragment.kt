package com.androidfpn.dreamapp.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.androidfpn.dreamapp.MyApplication
import com.androidfpn.dreamapp.R
import com.androidfpn.dreamapp.data.locale.entity.Sound
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories
import com.androidfpn.dreamapp.databinding.HomeFragmentBinding
import com.androidfpn.dreamapp.screen.home.adapter.HomeCategoryAdapter
import com.androidfpn.dreamapp.screen.home.adapter.HomeSoundsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var bestSoundAdapter: HomeSoundsAdapter
    private lateinit var newSoundAdapter: HomeSoundsAdapter
    private lateinit var categoryAdapter: HomeCategoryAdapter
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding

    private fun adapterOnClick(sound: Sound) {
        Toast.makeText(requireContext(), "Show", Toast.LENGTH_SHORT).show()
    }

    private fun categoryOnClick(category: SoundCategories) {
        Toast.makeText(requireContext(), "Show", Toast.LENGTH_SHORT).show()
    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory((requireActivity().application as MyApplication).soundRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapters()
        initializeCategories()
        initializeBestSounds()
        initializeSuggestedSounds()
    }

    private fun initializeAdapters() {
        binding?.let { binding ->
            val bestSoundRecyclerView: RecyclerView = binding.bestSoundsInclude.recycler
            binding.bestSoundsInclude.bestTv.text = getString(R.string.best_text)
            val newSoundRecyclerView: RecyclerView = binding.suggestedSoundInclude.recycler
            binding.suggestedSoundInclude.bestTv.text = getString(R.string.news_text)
            val categoryRecyclerView: RecyclerView = binding.categoryRecycler
            bestSoundAdapter = HomeSoundsAdapter { sound -> adapterOnClick(sound) }
            newSoundAdapter = HomeSoundsAdapter { sound -> adapterOnClick(sound) }
            categoryAdapter = HomeCategoryAdapter { categories -> categoryOnClick(categories) }
            bestSoundRecyclerView.adapter = bestSoundAdapter
            newSoundRecyclerView.adapter = newSoundAdapter
            categoryRecyclerView.adapter = categoryAdapter
        }
    }

    private fun initializeCategories() {
        viewModel.categoriesList.observe(viewLifecycleOwner) { items ->
            categoryAdapter.submitList(items)
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