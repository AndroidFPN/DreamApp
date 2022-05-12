package com.androidfpn.dreamapp.screen.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androidfpn.dreamapp.R
import com.androidfpn.dreamapp.databinding.HomeFragmentBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }


    private val viewModel by viewModels<HomeViewModel>()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding?.root

        viewModel.chipsData.observe(viewLifecycleOwner) { items ->
            items.forEach { chipsText ->
                binding?.chipGroup?.addView(createTagChip(requireContext(), chipsText))
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