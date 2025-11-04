package com.work.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.work.data.lds.CharacterDao
import com.work.data.lds.models.CharacterDomain
import com.work.data.lds.models.CharacterEntity
import com.work.data.network.NetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val api: NetworkApi,
    private val dao: CharacterDao
) : CharacterRepository {

    override fun getCharacters(): Flow<PagingData<CharacterDomain>> {
        return getPagingSource { dao.getAll() }
    }

    override fun searchAndFilter(
        name: String?, status: String?, species: String?, type: String?, gender: String?
    ): Flow<PagingData<CharacterDomain>> {
        return getPagingSource {
            dao.filter(name, status, species, type, gender)
        }
    }

    private fun getPagingSource(
        sourceFactory: () -> PagingSource<Int, CharacterEntity>
    ): Flow<PagingData<CharacterDomain>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = sourceFactory
        ).flow.map { pagingData ->
            pagingData.map { CharacterMapper.toDomain(it) }
        }
    }

    override suspend fun syncWithNetwork() {
        try {
            var page = 1
            var hasNext = true
            while (hasNext) {
                val response = api.getCharacters(page)
                val entities = response.results.map { CharacterMapper.toEntity(it) }
                dao.insertAll(entities)
                hasNext = response.info.next != null
                page++
            }
        } catch (e: Exception) {
        }
    }
}