package com.work.data.lds

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.work.data.lds.models.CharacterEntity

@Dao
interface CharacterDao {

    // ---------- Пагинация по id ----------
    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    // ---------- Фильтры ----------
    @Query(
        """
        SELECT * FROM characters 
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
          AND (:status IS NULL OR status = :status)
          AND (:species IS NULL OR species = :species)
          AND (:type IS NULL OR type = :type)
          AND (:gender IS NULL OR gender = :gender)
        ORDER BY id ASC
        """
    )
    fun filter(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAll()
}