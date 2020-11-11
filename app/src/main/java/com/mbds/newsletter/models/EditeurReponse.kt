package com.mbds.newsletter.models

import com.google.gson.annotations.SerializedName

data class EditeurReponse(
    val status: String,
    @SerializedName("sources")
    val editeur : List<Editeur>
)