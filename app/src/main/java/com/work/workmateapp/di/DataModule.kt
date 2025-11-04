package com.work.workmateapp.di

import com.work.data.CharacterRepository
import com.work.data.network.HttpClient
import com.work.data.network.NetworkApi
import com.work.data.network.CharacterRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single<NetworkApi> { HttpClient.api }
    single<CharacterRepository> { CharacterRepositoryImpl(api = get()) }

}