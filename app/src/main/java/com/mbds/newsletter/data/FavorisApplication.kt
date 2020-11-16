package com.mbds.newsletter.data

import android.app.Application
import com.mbds.newsletter.data.service.FavorisRepository
import com.mbds.newsletter.models.FavorisRoomDatabase

class FavorisApplication : Application() {

    val database by lazy { FavorisRoomDatabase.getDatabase(this) }
    val repository by lazy { FavorisRepository(database.favorisDao()) }

}