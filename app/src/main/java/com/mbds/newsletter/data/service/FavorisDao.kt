package com.mbds.newsletter.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbds.newsletter.models.Favoris


@Dao
interface FavorisDao {

    @Query("SELECT * FROM liste_des_favoris ORDER BY article ASC")
    fun getAlphabetizedWords(): List<Favoris>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Favoris)

    @Query("DELETE FROM liste_des_favoris")
    suspend fun deleteAll()

}