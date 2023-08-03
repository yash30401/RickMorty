package com.devyash.rickmorty.domain

import com.devyash.ExampleQuery

interface CharacterClient {
    suspend fun getCharacters():List<ExampleQuery.Characters>
}