package com.work.data.network

import com.work.data.CharacterRepository
import com.work.data.NetworkApi
import com.work.data.network.models.MyCharacter

class CharacterRepositoryImpl (
    private val api: NetworkApi
) : CharacterRepository {
    override suspend fun getAllCharacters(): List<MyCharacter> {
        return api.getAllCharacters().results
    }
}