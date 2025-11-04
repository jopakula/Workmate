package com.work.data

import com.work.data.network.RequestState
import com.work.data.network.models.MyCharacter

interface CharacterRepository {
    suspend fun getAllCharacters(page: Int): RequestState<List<MyCharacter>>
}