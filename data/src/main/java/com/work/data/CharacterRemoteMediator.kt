package com.work.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.work.data.lds.CharacterDao
import com.work.data.lds.models.CharacterEntity
import com.work.data.network.NetworkApi
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val api: NetworkApi,
    private val dao: CharacterDao
) : RemoteMediator<Int, CharacterEntity>() {

    private var page = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            when (loadType) {
                LoadType.REFRESH -> {
                    page = 1
                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        page = 1
                    } else {
                        page = (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val response = try {
                api.getCharacters(page)
            } catch (e: IOException) {
                return MediatorResult.Success(endOfPaginationReached = true)
            } catch (e: Exception) {
                return MediatorResult.Error(e)
            }

            val characters = response.results.map { CharacterMapper.toEntity(it) }
            dao.insertAll(characters)

            page++
            val endOfPagination = response.info.next == null
            MediatorResult.Success(endOfPaginationReached = endOfPagination)

        } catch (e: Exception) {
            MediatorResult.Success(endOfPaginationReached = true)
        }
    }
}