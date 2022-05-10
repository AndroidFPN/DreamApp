package com.androidfpn.dreamapp.screen.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androidfpn.dreamapp.MyApplication
import com.androidfpn.dreamapp.R
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory((requireActivity().application as MyApplication).soundRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.chipsData.observe(viewLifecycleOwner) { items ->
            items.forEach { chipsText ->
                chipGroup.addView(createTagChip(requireContext(), chipsText.name))
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