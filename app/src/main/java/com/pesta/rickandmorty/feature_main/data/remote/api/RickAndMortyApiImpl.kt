package com.pesta.rickandmorty.feature_main.data.remote.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.pesta.rickandmorty.CharactersQuery
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
}

fun CharactersQuery.Result.toCharacterDto() = CharacterDto(
    id?.toInt(),
    image = image,
    name = name
)