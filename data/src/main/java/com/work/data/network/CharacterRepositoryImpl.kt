package com.work.data.network

import com.work.data.CharacterRepository
import com.work.data.network.models.MyCharacter

class CharacterRepositoryImpl (
    private val api: NetworkApi
) : CharacterRepository {
    override suspend fun getAllCharacters(page: Int): RequestState<List<MyCharacter>> {
        return try {
            val response = api.getCharacters(page = page)
            if (response.results.isEmpty()) {
                RequestState.Empty
            } else {
                RequestState.Success(response.results)
            }
        } catch (e: Exception) {
            RequestState.Error(e.localizedMessage ?: "Unknown error")
        }
    }

}
