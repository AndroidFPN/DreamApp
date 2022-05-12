package com.androidfpn.dreamapp.screen.home

import androidx.lifecycle.*
import com.androidfpn.dreamapp.data.SoundRepository
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class HomeViewModel(private val repository: SoundRepository) : ViewModel() {
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {


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