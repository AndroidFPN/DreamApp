package com.androidfpn.dreamapp.data

import com.androidfpn.dreamapp.data.locale.dao.SoundCategoriesDao
import com.androidfpn.dreamapp.data.locale.dao.SoundDao
import com.androidfpn.dreamapp.data.locale.entity.Sound
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SoundRepository @Inject constructor(private val soundCategoriesDao: SoundCategoriesDao){
class SoundRepository(private val soundCategoriesDao: SoundCategoriesDao, private val soundDoa: SoundDao) {

    val categories: Flow<List<SoundCategories>> = soundCategoriesDao.getSoundCategories()

    fun fetchSoundListFilteredWithId(soundsId: List<Int>): Flow<List<Sound>> {
        return soundDoa.getSoundListFilteredWithId(soundsId)
    }

}