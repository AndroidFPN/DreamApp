package com.androidfpn.dreamapp.screen.home

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import com.androidfpn.dreamapp.MyApplication
import com.androidfpn.dreamapp.R
import com.androidfpn.dreamapp.databinding.HomeFragmentBinding
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory((requireActivity().application as MyApplication).soundRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding?.root


        viewModel.chipsData.observe(viewLifecycleOwner) { items ->
            items.forEach { chipsText ->
                binding?.chipGroup?.addView(createTagChip(requireContext(), chipsText.name))
            }
        }


        return view

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