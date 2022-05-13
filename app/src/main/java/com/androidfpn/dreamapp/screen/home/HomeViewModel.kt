package com.androidfpn.dreamapp.screen.home

import android.app.Application
import androidx.lifecycle.*
import com.androidfpn.dreamapp.MyApplication
import com.androidfpn.dreamapp.R
import com.androidfpn.dreamapp.data.SoundRepository
import com.androidfpn.dreamapp.data.locale.entity.Sound
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, private val repository: SoundRepository) : AndroidViewModel(application) {

    val chipsData: LiveData<List<SoundCategories>> = repository.categories.asLiveData()
    lateinit var bestSoundsList: LiveData<List<Sound>>

    init {
        initBestSounds()
    }

    private fun initBestSounds() {
        bestSoundsList =
            repository.fetchSoundListFilteredWithId((getApplication<Application>().applicationContext.resources.getIntArray(R.array.best_sounds)).toList())
                .asLiveData()
    }

    class HomeViewModelFactory(val application1: Application, private val repository: SoundRepository) : ViewModelProvider.AndroidViewModelFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(application = application1, repository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }

    }
}