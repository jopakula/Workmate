package com.work.data

import com.work.data.network.models.CharacterResponse
import retrofit2.http.GET

interface NetworkApi {
    @GET("character")
    suspend fun getAllCharacters(): CharacterResponse
}