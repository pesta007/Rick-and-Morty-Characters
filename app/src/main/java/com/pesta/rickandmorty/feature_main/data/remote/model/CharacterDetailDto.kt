package com.pesta.rickandmorty.feature_main.data.remote.model

data class CharacterDetailDto(
    val id: Int?,
    val name: String?,
    val image: String?,
    val isAlive: String?,
    val species: String?,
    val gender: String?,
    val origin: String?,
    val location: String?
)