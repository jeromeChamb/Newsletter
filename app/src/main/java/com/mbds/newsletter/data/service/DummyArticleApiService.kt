package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.Article

class DummyArticleApiService: ArticleService {
     val article: List<Article>
        get() = DUMMY_ArticleS

    override fun getArticles(): List<Article> {
        TODO("Not yet implemented")
    }

    private val DUMMY_ArticleS : MutableList<Article> = mutableListOf(
        Article(
            1,
            "Article 1",
            "https://i.picsum.photos/id/1011/5472/3648.jpg?hmac=Koo9845x2akkVzVFX3xxAc9BCkeGYA9VRVfLE4f0Zzk",
            "hiouzvrbyuicnux ue yviycafxniomd fy ozi uofhcdfg u^p"
        ),
        Article(
            2,
            "Article 2",
            "https://i.picsum.photos/id/1011/5472/3648.jpg?hmac=Koo9845x2akkVzVFX3xxAc9BCkeGYA9VRVfLE4f0Zzk",
            "hiouzvrbyuicnux ue yviycafxniomd fy ozi uofhcdfg u^p"
        ),
        Article(
            3,
            "Article 3",
            "https://i.picsum.photos/id/1011/5472/3648.jpg?hmac=Koo9845x2akkVzVFX3xxAc9BCkeGYA9VRVfLE4f0Zzk",
            "hiouzvrbyuicnux ue yviycafxniomd fy ozi uofhcdfg u^p"
        )
    )


}