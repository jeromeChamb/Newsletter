package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.ArticleReponse

interface ArticleService {
    fun getArticles(): ArticleReponse
}