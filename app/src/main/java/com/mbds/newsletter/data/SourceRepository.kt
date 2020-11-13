package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.SourceOnlineService
import com.mbds.newsletter.models.ArticleReponse

class SourceRepository {
    private val apiService: SourceOnlineService

    init {
        apiService = SourceOnlineService()
    }

    fun getArticles(subject :String): ArticleReponse = apiService.getArticles(subject)


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