package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.ArticleService
import com.mbds.newsletter.data.service.DummyArticleApiService
import com.mbds.newsletter.models.Article

class ArticleRepository {
    private val apiService: ArticleService

    init {
        apiService = DummyArticleApiService()
    }

    fun getArticles(): List<Article> = apiService.article

    companion object {
        private var instance: ArticleRepository? = null
        fun getInstance(): ArticleRepository {
            if (instance == null) {
                instance = ArticleRepository()
            }
            return instance!!
        }
    }
}