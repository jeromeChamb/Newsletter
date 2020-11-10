package com.mbds.newsletter.data

import com.mbds.newsletter.data.service.EditeurOnlineService
import com.mbds.newsletter.models.EditeurReponse

class EditorRepository {
    private val apiService: EditeurOnlineService

    init {
        apiService = EditeurOnlineService()
    }

    fun getEditeur(): EditeurReponse = apiService.getEditeur()


    companion object {
        private var instance: EditorRepository? = null
        fun getInstance(): EditorRepository {
            if (instance == null) {
                instance = EditorRepository()
            }
            return instance!!
        }
    }
}