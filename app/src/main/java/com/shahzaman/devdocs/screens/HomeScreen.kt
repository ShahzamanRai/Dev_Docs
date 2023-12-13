package com.shahzaman.devdocs.screens

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shahzaman.devdocs.R
import com.shahzaman.devdocs.components.LanguageCard
import com.shahzaman.devdocs.language.languageList

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onNavigateToWeb: (String) -> Unit,
    navHostController: NavHostController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    actions = {
                        IconButton(onClick = { navHostController.navigate("setting") }) {
                            Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                        }
                    },

                    )
            }
        ) { paddingValues ->
            FlowRow(
                maxItemsInEachRow = 2,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 6.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                languageList.forEachIndexed { _, languages ->
                    LanguageCard(
                        name = languages.title,
                        contentDescription = languages.title,
                        imageResId = languages.image,
                        modifier = Modifier.weight(1f)
                    ) {
                        onNavigateToWeb(languages.url)
                    }
                }
            }
        }
    }
}
