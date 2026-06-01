package com.corryn.composenavigationplayground.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corryn.composenavigationplayground.model.entity.BookEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class FavoritesLoadingState {
    data object None: FavoritesLoadingState()
    data object Loading: FavoritesLoadingState()
    data class Ready(
        val favorites: List<BookEntity>
    )
}

class FavoritesViewModel: ViewModel() {

    private val _favorites: MutableStateFlow<FavoritesLoadingState> = MutableStateFlow(FavoritesLoadingState.None)
    val favorites = _favorites.asStateFlow()

    fun getFavoriteBooks() {
        viewModelScope.launch {
            _favorites.emit(FavoritesLoadingState.Loading)

        }
    }

}