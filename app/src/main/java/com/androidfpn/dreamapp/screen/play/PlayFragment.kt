package com.androidfpn.dreamapp.screen.play

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidfpn.dreamapp.R
import com.androidfpn.dreamapp.databinding.PlayFragmentBinding
import com.androidfpn.dreamapp.util.AudioPlayer
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayFragment : Fragment() {

    @Inject
    lateinit var audioPlayer: AudioPlayer

    companion object {
        fun newInstance() = PlayFragment()
    }

    private val binding: PlayFragmentBinding get() = _binding
    private lateinit var _binding: PlayFragmentBinding
    private lateinit var viewModel: PlayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlayFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.videoPlayerView.player = audioPlayer.exoPlayer
        audioPlayer.audioFile="android.resource://com.androidfpn.dreamapp/raw/sound_1"
        audioPlayer.startPlayer()
        val findViewById = requireActivity().findViewById<ImageButton>(R.id.btnTimer)
//        val exo_play = requireActivity().findViewById<ImageButton>(R.id.exo_play)
//        exo_play.setOnClickListener {
//            audioPlayer.startPlayer()
//        }
        findViewById.setOnClickListener {
            Log.d("Neda", "Neda")
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}