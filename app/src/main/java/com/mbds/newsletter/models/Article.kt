package com.mbds.newsletter.models

import java.util.*


data class Article(
    val source: Objects,
    val author:String,
    val title: String,
    val description:String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)