package com.androidfpn.dreamapp.screen.home

import androidx.lifecycle.*
import com.androidfpn.dreamapp.data.SoundRepository
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories

class HomeViewModel(private val repository: SoundRepository) : ViewModel() {


    val chipsData : LiveData<List<SoundCategories>> = repository.categories.asLiveData()

    init {
    }

    class HomeViewModelFactory(private val repository: SoundRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }

    }
}