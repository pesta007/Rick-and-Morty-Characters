package com.pesta.rickandmorty.feature_main.di

import com.apollographql.apollo3.ApolloClient
import com.pesta.rickandmorty.feature_main.data.remote.api.RickAndMortyApi
import com.pesta.rickandmorty.feature_main.data.remote.api.RickAndMortyApiImpl
import com.pesta.rickandmorty.feature_main.data.repository.RickAndMortyRepositoryImpl
import com.pesta.rickandmorty.feature_main.domain.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRickAndMortyClient(): ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }

    @Singleton
    @Provides
    fun provideRickAndMortyApi(rickAndMortyApiImpl: RickAndMortyApiImpl): RickAndMortyApi = rickAndMortyApiImpl

    @Singleton
    @Provides
    fun provideRickAndMortyRepository(rickAndMortyRepositoryImpl: RickAndMortyRepositoryImpl): RickAndMortyRepository = rickAndMortyRepositoryImpl
}