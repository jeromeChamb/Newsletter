package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.EditeurReponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitEditeur {
    @GET("/v2/sources")
    fun editeur() : Call<EditeurReponse>
}