package com.work.data

import androidx.paging.ExperimentalPagingApi
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

@OptIn(ExperimentalPagingApi::class)
class CharacterRepositoryImpl(
    private val api: NetworkApi,
    private val dao: CharacterDao
) : CharacterRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCharacters(): Flow<PagingData<CharacterDomain>> {
        val localPagingSource: () -> PagingSource<Int, CharacterEntity> = {
            dao.pagingSource()
        }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = CharacterRemoteMediator(api, dao),
            pagingSourceFactory = localPagingSource
        ).flow
            .map { pagingData -> pagingData.map { CharacterMapper.toDomain(it) } }
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
    ): Flow<PagingData<CharacterDomain>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = sourceFactory
    ).flow
        .map { pagingData -> pagingData.map { CharacterMapper.toDomain(it) } }
}