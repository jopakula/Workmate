package com.work.workmateapp.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.data.CharacterRepository
import com.work.data.network.RequestState
import com.work.data.network.models.MyCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CharacterRepository
): ViewModel() {

    private val charactersStateMutable = MutableStateFlow<RequestState<List<MyCharacter>>>(RequestState.Idle)
    val charactersState: StateFlow<RequestState<List<MyCharacter>>> = charactersStateMutable

    private val charactersList = mutableListOf<MyCharacter>()

    private var currentPage = 1

    private var isLastPage = false
    private var isLoading = false

    fun loadCharacters() {
        if (isLoading || isLastPage) return

        isLoading = true
        charactersStateMutable.value = RequestState.Loading

        viewModelScope.launch {
            val request = repository.getAllCharacters(page = currentPage)
            when (request) {
                is RequestState.Success -> {
                    if (request.data.isEmpty()) {
                        isLastPage = true
                    } else {
                        charactersList.addAll(request.data)
                        charactersStateMutable.value = RequestState.Success(charactersList.toList())
                        currentPage++
                    }
                }
                is RequestState.Error -> {
                    charactersStateMutable.value = RequestState.Error(request.message)
                }
                else -> {}
            }
            isLoading = false
        }
    }
}