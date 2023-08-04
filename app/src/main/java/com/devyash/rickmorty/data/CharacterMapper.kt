package com.devyash.rickmorty.data

import com.devyash.ExampleQuery
import com.devyash.rickmorty.domain.CharacterDetailed
import com.devyash.rickmorty.domain.CharacterSurface

fun ExampleQuery.Result.toSurafceCharacter():CharacterSurface {
    return CharacterSurface(
        image = image!!,
        name = name!!,
        origin = origin?.name!!,
        species = species!!,
        location = location?.name!!
    )
}

