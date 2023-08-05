package com.devyash.rickmorty.domain

class GetCharacterUseCase(
    private val characterClient: CharacterClient
) {

    suspend fun execute(page:Int): List<CharacterSurface> {
        return characterClient.getCharacters(page)
    }
}