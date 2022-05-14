package com.androidfpn.dreamapp.screen.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.androidfpn.dreamapp.data.SoundRepository
import com.androidfpn.dreamapp.data.locale.entity.Sound
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: SoundRepository) : ViewModel() {

    val chipsData: LiveData<List<SoundCategories>> = repository.categories.asLiveData()
    lateinit var bestSoundsList: LiveData<List<Sound>>

    init {
        initBestSounds()
    }

    private fun initBestSounds() {
        bestSoundsList =
            repository.fetchSoundListFilteredWithId(repository.getBestCategory())
                .asLiveData()
    }

}

class HomeViewModelFactory(private val repository: SoundRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown VieModel Class")
    }

}