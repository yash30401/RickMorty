package com.devyash.rickmorty.data

import com.apollographql.apollo3.ApolloClient
import com.devyash.ExampleQuery
import com.devyash.rickmorty.domain.CharacterClient
import com.devyash.rickmorty.domain.CharacterDetailed
import com.devyash.rickmorty.domain.CharacterSurface

class ApolloCharacterClient(private val apolloClient: ApolloClient):CharacterClient{
    override suspend fun getCharacters(page: Int): List<CharacterSurface> {
        return apolloClient.query(ExampleQuery(1))
            .execute()
            .data
            ?.characters?.results
            ?.map{
                it!!.toSurafceCharacter()
            }
            ?: emptyList()
    }


}