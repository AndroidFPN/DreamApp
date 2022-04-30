package com.androidfpn.dreamapp.screen.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidfpn.dreamapp.R
import com.androidfpn.dreamapp.databinding.HomeFragmentBinding
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding?.root

        setupChipsGroup()

        return view

    }

    private fun setupChipsGroup() {
        val items = arrayOf("خواب آرام", "صبح انرژیک", "مدیتیشن", "بی استرس")
        items.forEach {
            tagName->
            binding?.chipGroup?.addView(createTagChip(requireContext(),tagName))
        }

    }

    private fun createTagChip(context: Context,chipName:String) : Chip{
        return Chip(context).apply {
            text = chipName
            setChipBackgroundColorResource(R.color.purple_200)
            isCloseIconVisible=false
            setTextColor(ContextCompat.getColor(context,R.color.white))
            id = ViewCompat.generateViewId()

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}