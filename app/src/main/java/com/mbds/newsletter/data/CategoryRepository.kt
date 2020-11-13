package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.CategoryOnlineService
import com.mbds.newsletter.data.service.SourceOnlineService
import com.mbds.newsletter.models.ArticleReponse

class CategoryRepository {
    private val apiService: CategoryOnlineService

    init {
        apiService = CategoryOnlineService()
    }

    fun getArticles(subject: String): ArticleReponse = apiService.getArticles(subject)


    companion object {
        private var instance: CategoryRepository? = null
        fun getInstance(): CategoryRepository {
            if (instance == null) {
                instance = CategoryRepository()
            }
            return instance!!
        }
    }
}