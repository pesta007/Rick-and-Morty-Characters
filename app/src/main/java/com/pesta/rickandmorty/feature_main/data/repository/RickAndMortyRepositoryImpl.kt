package com.pesta.rickandmorty.feature_main.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.pesta.rickandmorty.feature_main.data.paging.RickAndMortyPagingSource
import com.pesta.rickandmorty.feature_main.data.remote.model.CharacterDto
import com.pesta.rickandmorty.feature_main.domain.model.Character
import com.pesta.rickandmorty.feature_main.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val pagingSource: RickAndMortyPagingSource
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
}

fun CharacterDto.toCharacter(): Character{
   return Character(name!!, image!!)
}