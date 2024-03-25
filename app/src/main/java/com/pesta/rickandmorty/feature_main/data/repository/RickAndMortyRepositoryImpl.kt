package com.pesta.rickandmorty.feature_main.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.pesta.rickandmorty.feature_main.data.paging.RickAndMortyPagingSource
import com.pesta.rickandmorty.feature_main.data.remote.api.RickAndMortyApi
import com.pesta.rickandmorty.feature_main.data.remote.model.CharacterDetailDto
import com.pesta.rickandmorty.feature_main.data.remote.model.CharacterDto
import com.pesta.rickandmorty.feature_main.domain.model.Character
import com.pesta.rickandmorty.feature_main.domain.model.CharacterDetail
import com.pesta.rickandmorty.feature_main.domain.model.Gender
import com.pesta.rickandmorty.feature_main.domain.model.Status
import com.pesta.rickandmorty.feature_main.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val pagingSource: RickAndMortyPagingSource,
    private val api: RickAndMortyApi
) : RickAndMortyRepository {
    override fun getCharacters(): Flow<PagingData<Character>> =
        Pager(
            pagingSourceFactory = {
                pagingSource
            },
            config = PagingConfig(pageSize = 20)
        ).flow
            .map {pagingData: PagingData<CharacterDto> ->
                pagingData.filter { it.image!=null && it.name!=null }.map { it.toCharacter() }
            }

    override suspend fun getCharacter(id: Int): CharacterDetail {
        return api.getCharacter(id)?.toCharacterDetail() ?: emptyCharacter()

    }
}

fun CharacterDto.toCharacter(): Character{
   return Character(
       id!!,
       name!!,
       image!!
   )
}

fun CharacterDetailDto.toCharacterDetail() = CharacterDetail(
    id = id ?: -1,
    name = name ?: "",
    imageUrl = image ?: "",
    status = if (isAlive == "Dead") Status.DEAD else Status.ALIVE,
    species = species ?: "",
    gender = when(gender){
        "Male" -> Gender.MALE
        "Female" -> Gender.FEMALE
        else -> Gender.OTHER
    },
    origin = origin ?: "",
    lastSeen = location ?: ""
)

fun emptyCharacter(
    id: Int = -1,
    name: String = "",
    imageUrl: String = "",
    status: Status = Status.ALIVE,
    species: String = "",
    gender: Gender = Gender.OTHER,
    origin: String = "",
    lastSeen: String = ""
): CharacterDetail{
    return CharacterDetail(
        id, name, imageUrl, status, species, gender, origin, lastSeen
    )
}