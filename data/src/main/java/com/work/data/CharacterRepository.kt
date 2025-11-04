package com.work.data

import com.work.data.network.models.MyCharacter

interface CharacterRepository {
    suspend fun getAllCharacters(): List<MyCharacter>
}