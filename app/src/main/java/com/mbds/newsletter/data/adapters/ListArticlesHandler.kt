package com.mbds.newsletter.data.adapters

import com.mbds.newsletter.models.Article

interface ListArticlesHandler {
    fun showArticle(article: Article)
    fun back()
}