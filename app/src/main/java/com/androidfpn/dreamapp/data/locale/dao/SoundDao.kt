package com.androidfpn.dreamapp.data.locale.dao

import androidx.room.*
import com.androidfpn.dreamapp.data.locale.Model.SuggestedSoundInfo
import com.androidfpn.dreamapp.data.locale.entity.Sound

@Dao
interface SoundDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSound(sound: Sound)

    @Delete
    suspend fun deleteSound(sound: Sound)

    @Query(
        "SELECT " +
                "suggest_categories.id AS suggestCategoryId " +
                "suggest_categories.name AS suggestCategoryName" +
                "sound.id AS soundId" +
                "sound.title AS soundTitle" +
                "sound.image AS soundImage" +
                "FROM suggest_categories " +
                "JOIN sound ON sound.suggestCategoryFK= suggest_categories.id " +
                "ORDER BY suggest_categories.id ASC"
    )
    suspend fun getSuggestedSoundsInfo(): List<SuggestedSoundInfo>
}