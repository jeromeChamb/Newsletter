package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.CategoryOnlineService
import com.mbds.newsletter.data.service.CountryOnlinService
import com.mbds.newsletter.models.ArticleReponse

class CountryRepository {
    private val apiService: CountryOnlinService

    init {
        apiService = CountryOnlinService()
    }

    fun getArticles(subject: String): ArticleReponse = apiService.getArticles(subject)


    companion object {
        private var instance: CountryRepository? = null
        fun getInstance(): CountryRepository {
            if (instance == null) {
                instance = CountryRepository()
            }
            return instance!!
        }
    }
}