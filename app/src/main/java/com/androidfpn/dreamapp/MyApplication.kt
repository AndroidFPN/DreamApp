package com.androidfpn.dreamapp

import android.app.Application
import com.androidfpn.dreamapp.data.SoundRepository
import com.androidfpn.dreamapp.data.locale.db.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class MyApplication : Application() {

    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { AppDatabase.getDatabase(this) }
    public val soundRepository by lazy { SoundRepository(database.soundCategoriesDao(), database.soundDao()) }

