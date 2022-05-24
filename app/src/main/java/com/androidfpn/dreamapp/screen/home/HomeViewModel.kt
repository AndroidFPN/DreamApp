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

    private val bestSounds = mutableListOf(1, 3, 5)
    private val suggestedSounds = mutableListOf(2, 4)

//    val chipsData: LiveData<List<SoundCategories>> = repository.categories.asLiveData()
    lateinit var bestSoundsList: LiveData<List<Sound>>
    lateinit var suggestedSoundsList: LiveData<List<Sound>>
    lateinit var categoriesList: LiveData<List<SoundCategories>>

    init {
        initSounds()
    }

    private fun initSounds() {
        bestSoundsList = repository.fetchSoundListFilteredWithId(bestSounds).asLiveData()
        suggestedSoundsList = repository.fetchSoundListFilteredWithId(suggestedSounds).asLiveData()
        categoriesList = repository.categories.asLiveData()
    }

    class HomeViewModelFactory(private val repository: SoundRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }
    }
}