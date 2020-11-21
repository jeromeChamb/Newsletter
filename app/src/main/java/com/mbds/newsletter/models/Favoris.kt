package com.mbds.newsletter.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// data class Favoris(val article: String) {

    @Entity(tableName = "liste_des_favoris")
    class Favoris{

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "article")
        val article: String

    }

}