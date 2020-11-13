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
        private var instance: SourceRepository? = null
        fun getInstance(): SourceRepository {
            if (instance == null) {
                instance = SourceRepository()
            }
            return instance!!
        }
    }
}