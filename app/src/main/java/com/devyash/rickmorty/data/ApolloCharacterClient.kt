package com.devyash.rickmorty.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.devyash.ExampleQuery
import com.devyash.rickmorty.domain.CharacterClient
import com.devyash.rickmorty.domain.CharacterSurface
import javax.inject.Inject


class ApolloCharacterClient @Inject constructor(private val apolloClient: ApolloClient):CharacterClient{
    override suspend fun getCharacters(page: Int): List<CharacterSurface> {
        return apolloClient.query(ExampleQuery(Optional.presentIfNotNull(1)))
            .execute()
            .data
            ?.characters?.results
            ?.map{
                it!!.toSurafceCharacter()
            }
            ?: emptyList()
    }


}