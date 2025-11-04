package com.work.data.network.models

data class CharacterResponse(
    val info: Info,
    val results: List<MyCharacter>
)