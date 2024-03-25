package com.pesta.rickandmorty.feature_main.data.remote.api

import com.pesta.rickandmorty.feature_main.data.remote.model.CharacterDetailDto
import com.pesta.rickandmorty.feature_main.data.remote.model.CharacterDto

interface RickAndMortyApi {
    suspend fun getCharacters(page: Int): List<CharacterDto>
    suspend fun getCharacter(id: Int): CharacterDetailDto?
}