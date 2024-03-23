package com.pesta.rickandmorty.feature_main.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pesta.rickandmorty.feature_main.data.remote.api.RickAndMortyApi
import com.pesta.rickandmorty.feature_main.data.remote.model.CharacterDto
import javax.inject.Inject

class RickAndMortyPagingSource @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
): PagingSource<Int, CharacterDto>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { anchor->
            state.closestPageToPosition(anchor)?.nextKey?.minus(1)
                ?: state.closestPageToPosition(anchor)?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        val page = params.key ?: 1
        val characters = rickAndMortyApi.getCharacters(page = page)
        return LoadResult.Page(
            data = characters,
            prevKey = if (page == 1) null else page-1,
            nextKey = if (characters.isEmpty()) null else page+1
        )
    }
}