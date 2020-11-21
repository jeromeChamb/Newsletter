package com.mbds.newsletter.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mbds.newsletter.data.service.FavorisDao


@Database(entities = arrayOf(Favoris::class), version = 1, exportSchema = false)
class FavorisRoomDatabase : RoomDatabase() {

        abstract fun favorisDao(): FavorisDao

        companion object {
            // Singleton prevents multiple instances of database opening at the
            // same time.
            @Volatile
            private var INSTANCE: FavorisRoomDatabase? = null

            fun getDatabase(context: Context): FavorisRoomDatabase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavorisRoomDatabase::class.java,
                        "favoris_database"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }
    }

}