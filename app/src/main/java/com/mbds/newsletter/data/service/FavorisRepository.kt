package com.mbds.newsletter.data.service

import androidx.annotation.WorkerThread
import com.mbds.newsletter.models.Favoris
import java.util.concurrent.Flow

class FavorisRepository(private val favorisDao: FavorisDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Favoris>> = favorisDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(favoris: Favoris) {
        favorisDao.insert(favoris)
    }

}