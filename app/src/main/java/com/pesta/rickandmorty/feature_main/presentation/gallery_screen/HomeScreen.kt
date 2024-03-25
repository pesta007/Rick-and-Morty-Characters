package com.pesta.rickandmorty.feature_main.presentation.gallery_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.pesta.rickandmorty.feature_main.domain.model.Character
import com.pesta.rickandmorty.feature_main.presentation.gallery_screen.components.CharacterCard
import com.pesta.rickandmorty.feature_main.presentation.gallery_screen.components.ExpandedCharacterCard


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    var showCharacter by remember {
        mutableStateOf<Int?>(null)
    }
    Box(modifier = modifier){
        HomeScreenContent(
            onCharacterClicked = { id: Int->
                showCharacter = id
            }
        )
        if (showCharacter!=null){
            val viewModel: CharacterDetailViewModel = hiltViewModel()
            val character by viewModel.characterState
            LaunchedEffect(key1 = Unit) {
                viewModel.getCharacter(showCharacter!!)
            }
            ExpandedCharacterCard(
                name = character.name,
                imageUrl = character.imageUrl,
                alive = character.status.toString() ,
                gender = character.gender.toString(),
                species = character.species,
                lastSeen = character.lastSeen,
                origin = character.origin,
                modifier = Modifier.padding(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .clickable {
                        showCharacter = null
                    }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    viewModel: GalleryViewModel = hiltViewModel(),
    onCharacterClicked: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Rick And Morty") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            )
        }
    ) {
        val characters = viewModel.charactersFlow.collectAsLazyPagingItems()
        Gallery(characters = characters, modifier = Modifier.padding(it), onCharacterClicked = onCharacterClicked)
    }
}

@Composable
fun Gallery(
    characters: LazyPagingItems<Character>,
    modifier: Modifier,
    onCharacterClicked: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(characters.itemCount) {
            characters[it]?.let { it1 ->
                CharacterCard(
                    name = it1.name,
                    imageUrl = it1.imageUrl,
                    modifier = Modifier.clickable {
                        onCharacterClicked(it1.id)
                    }
                )
            }
        }
    }
}