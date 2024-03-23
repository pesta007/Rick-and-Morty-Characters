package com.pesta.rickandmorty.feature_main.presentation.gallery_screen

import androidx.lifecycle.ViewModel
import com.pesta.rickandmorty.feature_main.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
): ViewModel() {
    val charactersFlow = repository.getCharacters()
}