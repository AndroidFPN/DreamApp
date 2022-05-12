package com.androidfpn.dreamapp.data

import com.androidfpn.dreamapp.data.locale.dao.SoundCategoriesDao
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SoundRepository @Inject constructor(private val soundCategoriesDao: SoundCategoriesDao){

    val categories: Flow<List<SoundCategories>> = soundCategoriesDao.getSoundCategories()

}