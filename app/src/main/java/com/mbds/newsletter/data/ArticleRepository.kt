package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.ArticleService
import com.mbds.newsletter.data.service.RetrofitApiService
import com.mbds.newsletter.models.Article
import retrofit2.Call

class ArticleRepository {


    fun getArticles() {}

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