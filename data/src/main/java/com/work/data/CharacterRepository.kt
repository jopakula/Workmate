package com.work.data

import androidx.paging.PagingData
import com.work.data.lds.models.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<CharacterDomain>>
    fun searchAndFilter(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        type: String? = null,
        gender: String? = null
    ): Flow<PagingData<CharacterDomain>>

    suspend fun syncWithNetwork()
}