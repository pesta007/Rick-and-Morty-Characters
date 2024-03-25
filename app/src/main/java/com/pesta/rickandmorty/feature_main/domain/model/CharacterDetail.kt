package com.pesta.rickandmorty.feature_main.domain.model

data class CharacterDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val status: Status,
    val species: String,
    val gender: Gender,
    val origin: String,
    val lastSeen: String
)

enum class Status{
    ALIVE, DEAD
}

enum class Gender{
    MALE, FEMALE, OTHER
}
