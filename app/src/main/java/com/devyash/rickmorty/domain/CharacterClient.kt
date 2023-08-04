package com.devyash.rickmorty.domain

import com.devyash.ExampleQuery

interface CharacterClient {
    suspend fun getCharacters(page:Int):List<CharacterSurface>
}