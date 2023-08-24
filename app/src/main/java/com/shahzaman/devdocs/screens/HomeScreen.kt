package com.shahzaman.devdocs.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shahzaman.devdocs.components.LanguageCard
import com.shahzaman.devdocs.language.languageList

@Composable
fun HomeScreen(
    onNavigateToWeb: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column{
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(8.dp)
            ) {
                itemsIndexed(languageList) { _, item ->
                    LanguageCard(
                        name = item.title,
                        contentDescription = item.title,
                        imageResId = item.image
                    ) {
                        onNavigateToWeb(item.url)
                    }
                }
            }
        }
    }
}