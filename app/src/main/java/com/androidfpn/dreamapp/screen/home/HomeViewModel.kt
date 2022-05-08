package com.androidfpn.dreamapp.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {


    private val _chipsData = MutableLiveData<List<String>>()
    val chipsData: LiveData<List<String>>
        get() = _chipsData

    init {
        populateChipsData()
    }


    private fun populateChipsData() {
        // TODO: get list from repo
        val items = arrayOf("خواب آرام", "صبح انرژیک", "مدیتیشن", "بی استرس")
        _chipsData.value = items.asList()

    }
}