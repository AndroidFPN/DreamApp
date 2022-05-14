package com.androidfpn.dreamapp.di

import android.content.Context
import androidx.room.Room
import com.androidfpn.dreamapp.data.locale.dao.SoundCategoriesDao
import com.androidfpn.dreamapp.data.locale.dao.SoundDao
import com.androidfpn.dreamapp.data.locale.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideSoundCategoriesDao(appDatabase: AppDatabase): SoundCategoriesDao {
        return appDatabase.soundCategoriesDao()
    }

    @Provides
    fun provideSoundDao(appDatabase: AppDatabase):SoundDao{
        return appDatabase.soundDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "DreamSounds").build()
    }
}