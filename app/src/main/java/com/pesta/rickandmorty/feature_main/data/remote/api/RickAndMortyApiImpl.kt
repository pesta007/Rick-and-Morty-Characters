package com.pesta.rickandmorty.feature_main.data.remote.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.pesta.rickandmorty.CharacterQuery
import com.pesta.rickandmorty.CharactersQuery
import com.pesta.rickandmorty.feature_main.data.remote.model.CharacterDetailDto
import com.pesta.rickandmorty.feature_main.data.remote.model.CharacterDto
import javax.inject.Inject

class RickAndMortyApiImpl @Inject constructor(private val rickAndMortyClient: ApolloClient): RickAndMortyApi {
    override suspend fun getCharacters(page: Int): List<CharacterDto> {
        return rickAndMortyClient.query(CharactersQuery(page = Optional.Present(page)))
            .execute()
            .data
            ?.characters
            ?.results
            ?.mapNotNull { result ->
                result?.toCharacterDto()
            } ?: emptyList()
    }

    override suspend fun getCharacter(id: Int): CharacterDetailDto? {
        return rickAndMortyClient.query(CharacterQuery(id.toString()))
            .execute()
            .data
            ?.character?.toCharacterDetailDto()
    }
}

fun CharactersQuery.Result.toCharacterDto() = CharacterDto(
    id?.toInt(),
    image = image,
    name = name
)

fun CharacterQuery.Character.toCharacterDetailDto() = CharacterDetailDto(
    id = id?.toInt(),
    image = image,
    gender = gender,
    isAlive = status,
    location = location?.name,
    name = name,
    origin = origin?.name,
    species = species
)