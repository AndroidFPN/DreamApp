package com.androidfpn.dreamapp.data.locale.dao

import androidx.room.*
import com.androidfpn.dreamapp.data.locale.Model.SuggestedSoundInfo
import com.androidfpn.dreamapp.data.locale.entity.Sound
import kotlinx.coroutines.flow.Flow

@Dao
interface SoundDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSound(sound: Sound)

    @Delete
    suspend fun deleteSound(sound: Sound)
}