package com.androidfpn.dreamapp.data.locale.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidfpn.dreamapp.data.locale.dao.SoundCategoriesDao
import com.androidfpn.dreamapp.data.locale.dao.SoundDao
import com.androidfpn.dreamapp.data.locale.entity.Sound
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories
import com.androidfpn.dreamapp.data.locale.entity.SuggestCategories


@Database(
    entities = [Sound::class, SoundCategories::class, SuggestCategories::class],
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase : RoomDatabase() {

    abstract fun soundDao(): SoundDao
    abstract fun soundCategoriesDao(): SoundCategoriesDao

    companion object {
        private const val DATABASE_DIR = "database/DreamDb.db" // Asset/database/you_name.db

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "DreamDb"
                ).createFromAsset(DATABASE_DIR)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
