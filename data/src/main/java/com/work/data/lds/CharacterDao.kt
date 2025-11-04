package com.work.data.lds

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.work.data.lds.models.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters ORDER BY id")
    fun getAll(): PagingSource<Int, CharacterEntity>

    @Query("""
        SELECT * FROM characters 
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
        AND (:status IS NULL OR status = :status)
        AND (:species IS NULL OR species = :species)
        AND (:type IS NULL OR type = :type)
        AND (:gender IS NULL OR gender = :gender)
        ORDER BY id
    """)
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