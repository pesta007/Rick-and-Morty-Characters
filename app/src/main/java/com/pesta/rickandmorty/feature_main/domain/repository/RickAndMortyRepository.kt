package com.pesta.rickandmorty.feature_main.domain.repository

import androidx.paging.PagingData
import com.pesta.rickandmorty.feature_main.domain.model.Character
import com.pesta.rickandmorty.feature_main.domain.model.CharacterDetail
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    fun getCharacters() : Flow<PagingData<Character>>
    suspend fun getCharacter(id: Int): CharacterDetail
}