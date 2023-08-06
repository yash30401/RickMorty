package com.devyash.rickmorty.di

import com.apollographql.apollo3.ApolloClient
import com.devyash.rickmorty.data.ApolloCharacterClient
import com.devyash.rickmorty.domain.CharacterClient
import com.devyash.rickmorty.domain.GetCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApolloClient():ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun povideCharacterClient(apolloClient: ApolloClient):CharacterClient{
        return ApolloCharacterClient(apolloClient)
    }

    @Provides
    @Singleton
    fun povideGetCharactersUseCase(characterClient: CharacterClient):GetCharacterUseCase{
        return GetCharacterUseCase(characterClient)
    }

}