package com.androidfpn.dreamapp.data.locale.dao

import androidx.room.*
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories

@Dao
interface SoundCategoriesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSoundCategory(soundCategory: SoundCategories)

    @Delete
    suspend fun deleteSoundCategory(soundCategory: SoundCategories)

    @Query("SELECT * FROM sound_categories ")
    suspend fun getSoundCategories(): List<SoundCategories>
}