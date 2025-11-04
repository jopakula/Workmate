package com.work.workmateapp.di

import com.work.workmateapp.ui.screens.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MainViewModel(
            repository = get()
        )
    }

}