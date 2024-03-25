package com.pesta.rickandmorty.feature_main.presentation.gallery_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pesta.rickandmorty.feature_main.data.repository.emptyCharacter
import com.pesta.rickandmorty.feature_main.domain.model.CharacterDetail
import com.pesta.rickandmorty.feature_main.domain.model.Gender
import com.pesta.rickandmorty.feature_main.domain.model.Status
import com.pesta.rickandmorty.feature_main.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
): ViewModel() {
    val characterState = mutableStateOf(emptyCharacter())

    fun getCharacter(id: Int){
        viewModelScope.launch {
            val characterDetail = repository.getCharacter(id)
            characterState.value = characterDetail
        }
    }
}

