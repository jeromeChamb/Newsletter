package com.mbds.newsletter.models

data class ArticleReponse (
    val status: String,
    val totalResults: Int,

    val articles: List<Article>

)