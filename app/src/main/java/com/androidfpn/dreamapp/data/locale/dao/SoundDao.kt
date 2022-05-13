package com.androidfpn.dreamapp.data.locale.dao

import androidx.room.*
import com.androidfpn.dreamapp.data.locale.Model.SuggestedSoundInfo
import com.androidfpn.dreamapp.data.locale.entity.Sound
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface SoundDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSound(sound: Sound)

    @Delete
    suspend fun deleteSound(sound: Sound)

    @Query("SELECT * FROM sound Where id IN (:ids) ")
    fun getSoundListFilteredWithId(ids: List<Int>): Flow<List<Sound>>
}