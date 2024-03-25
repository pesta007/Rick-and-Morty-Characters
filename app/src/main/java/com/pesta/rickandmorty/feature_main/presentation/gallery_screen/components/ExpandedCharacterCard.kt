package com.pesta.rickandmorty.feature_main.presentation.gallery_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pesta.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun ExpandedCharacterCard(
    name: String,
    imageUrl: String,
    alive: String,
    gender: String,
    species: String,
    lastSeen: String,
    origin: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primary)
        ,

    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            modifier = Modifier.aspectRatio(16 / 17f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 15.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = species,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = gender,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = alive,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Text(
                text = "last seen: $lastSeen",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,

            )

            Text(
                text = "origin: $origin",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,

            )
        }

    }


}

