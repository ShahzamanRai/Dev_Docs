package com.shahzaman.devdocs.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shahzaman.devdocs.R
import com.shahzaman.devdocs.SettingsModel
import com.shahzaman.devdocs.components.AboutApp
import com.shahzaman.devdocs.components.ButtonGroupPref
import com.shahzaman.devdocs.components.SettingItem
import com.shahzaman.devdocs.preference.Preferences
import com.soloftech.keyboard.presentation.components.SettingsCategory
import kotlinx.coroutines.launch
import java.io.File
import java.text.DecimalFormat
import kotlin.math.log10
import kotlin.math.pow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceScreen(
    settingsModel: SettingsModel,
    navHostController: NavHostController,
    context: Context
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )
    var showAlertDialog by remember { mutableStateOf(false) }
    var showClearCacheDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var cacheSize = getFormattedCacheSize(context)
    val cacheTitle by remember { mutableStateOf("Free up $cacheSize of space") }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                LargeTopAppBar(
                    title = {
                        Text(text = stringResource(id = com.shahzaman.devdocs.R.string.preference))
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navHostController.popBackStack()
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                SettingsCategory(title = stringResource(id = com.shahzaman.devdocs.R.string.appearance))
                ButtonGroupPref(
                    preferenceKey = Preferences.themeKey,
                    title = stringResource(com.shahzaman.devdocs.R.string.theme),
                    options = listOf(
                        com.shahzaman.devdocs.R.string.system,
                        com.shahzaman.devdocs.R.string.light,
                        com.shahzaman.devdocs.R.string.dark
                    ).map {
                        stringResource(it)
                    },
                    values = listOf("system", "light", "dark"),
                    defaultValue = "system"
                ) {
                    settingsModel.themeMode = it
                }

                SettingsCategory(title = stringResource(id = com.shahzaman.devdocs.R.string.others))

                SettingItem(
                    title = stringResource(id = R.string.cache_title),
                    description = cacheTitle
                ) {
                    showClearCacheDialog = true
                }
                SettingItem(
                    title = stringResource(id = com.shahzaman.devdocs.R.string.copyright_title),
                    description = stringResource(id = com.shahzaman.devdocs.R.string.copyright_description)
                ) {}

                SettingItem(
                    title = stringResource(id = com.shahzaman.devdocs.R.string.policy_title),
                    description = stringResource(id = com.shahzaman.devdocs.R.string.policy_description)
                ) {
                    navHostController.navigate("policy")
                }
                SettingItem(
                    title = stringResource(id = com.shahzaman.devdocs.R.string.about),
                    description = stringResource(id = com.shahzaman.devdocs.R.string.about_description)
                ) {
                    showAlertDialog = true
                }
            }
        }
        if (showAlertDialog) {
            AlertDialog(
                onDismissRequest = { showAlertDialog = false },
                confirmButton = {
                    TextButton(onClick = { showAlertDialog = false }) {
                        Text(text = "OK", color = MaterialTheme.colorScheme.onSurface)
                    }
                },
                text = {
                    AboutApp()
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
        if (showClearCacheDialog) {
            AlertDialog(
                onDismissRequest = { showClearCacheDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        clearCache(context)
                        showClearCacheDialog = false
                        scope.launch {
                            snackbarHostState.showSnackbar("Cache cleared successfully.")
                        }
                        cacheSize = getFormattedCacheSize(context)
                    }) {
                        Text(text = "YES", color = MaterialTheme.colorScheme.onSurface)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showClearCacheDialog = false }) {
                        Text(text = "NO", color = MaterialTheme.colorScheme.onSurface)
                    }
                },
                title = {
                    Text(text = "Clear Cache")
                },
                text = {
                    Text(text = "Do you really want to clear cache?")
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

fun getFormattedCacheSize(context: Context): String {
    val cacheDir = context.cacheDir
    val externalCacheDir = context.externalCacheDir
    var cacheSize = getDirSize(cacheDir)
    externalCacheDir.let { cacheSize = cacheSize.plus(getDirSize(it!!)) }
    return readableFileSize(cacheSize)
}


fun clearCache(context: Context) {
    // Clear internal cache
    val internalCacheDir = context.cacheDir
    val internalFiles = internalCacheDir.listFiles()
    if (internalFiles != null) {
        for (file in internalFiles) {
            file.delete()
        }
    }

    // Clear external cache
    val externalCacheDir = context.externalCacheDir
    val externalFiles = externalCacheDir?.listFiles()
    if (externalFiles != null) {
        for (file in externalFiles) {
            file.delete()
        }
    }
}

fun getDirSize(dir: File): Long {
    var size: Long = 0
    for (file in dir.listFiles()!!) {
        if (file != null && file.isDirectory) {
            size += getDirSize(file)
        } else if (file != null && file.isFile) {
            size += file.length()
        }
    }
    return size
}

fun readableFileSize(size: Long): String {
    if (size <= 0) {
        return "0 Bytes"
    }
    val units = arrayOf("Bytes", "KB", "MB", "GB", "TB")
    val digitGroups = (log10(size.toDouble()) / log10(1024.0)).toInt()
    val stringBuilder = StringBuilder()
    val decimalFormat = DecimalFormat("#,##0.#")
    val d = size.toDouble()
    val pow = 1024.0.pow(digitGroups.toDouble())
    java.lang.Double.isNaN(d)
    stringBuilder.append(decimalFormat.format(d / pow))
    stringBuilder.append(" ")
    stringBuilder.append(units[digitGroups])
    return stringBuilder.toString()
}