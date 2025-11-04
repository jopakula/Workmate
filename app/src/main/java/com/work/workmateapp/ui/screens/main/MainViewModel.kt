package com.work.workmateapp.ui.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.data.CharacterRepository
import com.work.data.network.models.MyCharacter
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CharacterRepository
): ViewModel() {

    private val _characters = mutableStateOf<List<MyCharacter>>(emptyList())
    val characters: State<List<MyCharacter>> = _characters

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _selectedCard = mutableStateOf<Int?>(null)
    val selectedCard: State<Int?> = _selectedCard

    fun chooseCard(id: Int){
        _selectedCard.value = id
    }

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = repository.getAllCharacters()
                _characters.value = result
            } catch (e: Exception) {
                _error.value = e.message ?: "Ошибка загрузки"
            } finally {
                _isLoading.value = false
            }
        }
    }
}