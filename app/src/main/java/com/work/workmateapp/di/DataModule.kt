package com.work.workmateapp.di

import androidx.room.Room
import com.work.data.CharacterRepository
import com.work.data.network.HttpClient
import com.work.data.network.NetworkApi
import com.work.data.CharacterRepositoryImpl
import com.work.data.lds.CharacterDatabase
import org.koin.dsl.module

val dataModule = module {

    single { Room.databaseBuilder(get(), CharacterDatabase::class.java, "my_character_database").build() }

    single { get<CharacterDatabase>().characterDao() }

    single<NetworkApi> { HttpClient.api }
    single<CharacterRepository> { CharacterRepositoryImpl(api = get(), dao = get()) }

}