package com.work.data.network

import com.work.data.network.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse
}