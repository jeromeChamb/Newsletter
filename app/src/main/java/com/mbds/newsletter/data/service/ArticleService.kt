package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.Article

interface ArticleService {
    val article: List<Article>
    fun getArticles(): List<Article>
}