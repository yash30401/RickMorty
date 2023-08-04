package com.devyash.rickmorty.domain

import com.devyash.ExampleQuery

data class CharacterDetailed(
    val image: String,
    val name: String,
    val origin: ExampleQuery.Origin,
    val species: String,
    val location: ExampleQuery.Location
)