package com.work.data.lds

import androidx.room.Database
import androidx.room.RoomDatabase
import com.work.data.lds.models.CharacterEntity

const val DATABASE_VERSION = 1

@Database(entities = [CharacterEntity::class], version = DATABASE_VERSION, exportSchema = true)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}