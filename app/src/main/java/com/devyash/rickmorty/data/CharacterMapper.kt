package com.devyash.rickmorty.data

import com.devyash.ExampleQuery
import com.devyash.rickmorty.domain.CharacterDetailed
import com.devyash.rickmorty.domain.CharacterSurface

fun ExampleQuery.Result.toSurafceCharacter():CharacterSurface {
    return CharacterSurface(
        image = image?:"No Image",
        name = name?:"No Name",
        origin = origin?.name?:"No Origin",
        species = species?:"No Speices",
        location = location?.name?:"No Location"
    )
}

