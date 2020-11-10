package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.ArticleReponse
import com.mbds.newsletter.models.Editeur
import com.mbds.newsletter.models.EditeurReponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditeurOnlineService : EditeurService {
    private val service: RetrofitEditeur

    init {
        val retrofit = buildClient()
        //Init the api service with retrofit
        service = retrofit.create(RetrofitEditeur::class.java)
    }

    /**
     * Configure retrofit
     */
    private fun buildClient(): Retrofit {
        val httpClient = OkHttpClient.Builder().apply {
            addLogInterceptor(this)
            addApiInterceptor(this)
        }.build()
        return Retrofit
            .Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    /**
     * Add a logger to the client so that we log every request
     */
    private fun addLogInterceptor(builder: OkHttpClient.Builder) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addNetworkInterceptor(httpLoggingInterceptor)
    }

    /**
     * Intercept every request to the API and automatically add
     * the api key as query parameter
     */
    private fun addApiInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", apiKey)
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
    }

    companion object {
        private const val apiKey = "ad73e60d063446088a5ae051a33b5ae3"
        private const val apiUrl = "https://newsapi.org/"
    }

    override fun getEditeur(): EditeurReponse {
        val response = service.editeur().execute().body()
        return response!!
    }

}