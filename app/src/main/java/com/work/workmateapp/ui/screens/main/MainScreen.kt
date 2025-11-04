package com.work.workmateapp.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.work.data.network.RequestState
import com.work.data.network.models.MyCharacter
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    onClick: () -> Unit = {},
    mainViewModel: MainViewModel,
) {
    val charactersState by mainViewModel.charactersState.collectAsState()
    val listState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        mainViewModel.loadCharacters()

    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleItemIndex != null && lastVisibleItemIndex >= totalItems - 4) {
                    mainViewModel.loadCharacters()
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (charactersState) {
            is RequestState.Idle -> {

            }

            is RequestState.Loading -> {

            }

            is RequestState.Success -> {
                val characters = (charactersState as RequestState.Success<List<MyCharacter>>).data
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    state = listState,
                ) {
                    items(characters) { character ->
                        CharacterCard(
                            character = character,
                            onClick = { onClick() }
                        )
                    }
                }
            }

            is RequestState.Error -> {
                Text(
                    text = "Ошибка: ${(charactersState as RequestState.Error).message}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is RequestState.Empty -> {

            }

            else -> {}
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen(mainViewModel = koinViewModel())
}