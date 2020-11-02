package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.ArticleService
import com.mbds.newsletter.data.service.DummyArticleApiService

class ArticleRepository {
    private val apiService: ArticleService

    init {
        apiService = DummyArticleApiService()
    }


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