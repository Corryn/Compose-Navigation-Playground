package com.corryn.composenavigationplayground.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corryn.composenavigationplayground.model.entity.BookEntity
import com.corryn.composenavigationplayground.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class FavoritesLoadingState {
    data object None: FavoritesLoadingState()
    data object Loading: FavoritesLoadingState()
    data class Ready(
        val favorites: List<BookEntity>
    ): FavoritesLoadingState()
}

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: BookRepository
): ViewModel() {

    private val _favorites: MutableStateFlow<FavoritesLoadingState> = MutableStateFlow(FavoritesLoadingState.None)
    val favorites = _favorites.asStateFlow()

    fun getFavoriteBooks() {
        viewModelScope.launch {
            _favorites.emit(FavoritesLoadingState.Loading)

            val books = repository.getBooks()
            _favorites.emit(FavoritesLoadingState.Ready(favorites = books))
        }
    }

}