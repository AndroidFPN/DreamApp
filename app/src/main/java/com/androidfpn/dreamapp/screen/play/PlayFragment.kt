package com.androidfpn.dreamapp.screen.play

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidfpn.dreamapp.databinding.PlayFragmentBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class PlayFragment : Fragment() {

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
        val player: ExoPlayer = ExoPlayer.Builder(requireActivity()).build()
        binding.videoPlayerView.player = player

        // Build the media item.
        // Build the media item.
        val mediaItem: MediaItem =
            MediaItem.fromUri(Uri.parse("android.resource://com.androidfpn.dreamapp/raw/sound_1"))
// Set the media item to be played.
// Set the media item to be played.
        player.setMediaItem(mediaItem)
// Prepare the player.
// Prepare the player.
        player.prepare()
// Start the playback.
// Start the playback.

        player.play()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}