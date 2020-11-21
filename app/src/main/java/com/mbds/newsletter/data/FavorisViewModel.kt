package com.mbds.newsletter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mbds.newsletter.data.service.FavorisRepository
import com.mbds.newsletter.models.Favoris

class FavorisViewModel (private val repository: FavorisRepository) : ViewModel() {

    val allWords: LiveData<List<Favoris>> = repository.allWords.asLiveData()
    fun insert(favoris: Favoris) = viewModelScope.launch {
        repository.insert(favoris)
    }
}

class FavorisViewModelFactory(private val repository: FavorisRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavorisViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavorisViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}