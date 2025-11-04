package com.work.workmateapp.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.work.data.CharacterRepository
import com.work.data.lds.models.CharacterDomain
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val repository: CharacterRepository
): ViewModel() {

    val characters: Flow<PagingData<CharacterDomain>> = repository.getCharacters()


}