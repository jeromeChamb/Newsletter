package com.mbds.newsletter.models

import androidx.annotation.StringRes

data class Article(
    val id: Int,
    val name: String,
    val author:String,
    val title: String,
    val description:String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)