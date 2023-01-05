package com.example.devdocs

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.devdocs.ui.theme.DevDocsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevDocsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        customRow(
                            firstResourceID = R.drawable.android,
                            firstName = "Android",
                            firstIntentValue = "android",
                            secondResourceID = R.drawable.compose,
                            secondName = "Jetpack Compose",
                            secondIntentValue = "compose"
                        )
                        customRow(
                            firstResourceID = R.drawable.material,
                            firstName = "Material 3",
                            firstIntentValue = "material",
                            secondResourceID = R.drawable.tensorflow,
                            secondName = "Tensorflow",
                            secondIntentValue = "tensorflow"
                        )
                        customRow(
                            firstResourceID = R.drawable.kotlin,
                            firstName = "Kotlin",
                            firstIntentValue = "kotlin",
                            secondResourceID = R.drawable.java,
                            secondName = "Java",
                            secondIntentValue = "java"
                        )
                        customRow(
                            firstResourceID = R.drawable.python,
                            firstName = "Python",
                            firstIntentValue = "python",
                            secondResourceID = R.drawable.javascript,
                            secondName = "JavaScript",
                            secondIntentValue = "javascript"
                        )
                        customRow(
                            firstResourceID = R.drawable.typescript,
                            firstName = "Typescript",
                            firstIntentValue = "typescript",
                            secondResourceID = R.drawable.react,
                            secondName = "React",
                            secondIntentValue = "react"
                        )
                        customRow(
                            firstResourceID = R.drawable.angular,
                            firstName = "Angular",
                            firstIntentValue = "angular",
                            secondResourceID = R.drawable.swift,
                            secondName = "Swift",
                            secondIntentValue = "swift"
                        )
                        customRow(
                            firstResourceID = R.drawable.lua,
                            firstName = "Lua",
                            firstIntentValue = "lua",
                            secondResourceID = R.drawable.arch,
                            secondName = "Arch",
                            secondIntentValue = "arch"
                        )
                    }
                }
            }
        }
    }
    
    @Composable
    fun customRow(
        firstResourceID: Int,
        firstName: String,
        firstIntentValue: String,
        secondResourceID: Int,
        secondName: String,
        secondIntentValue: String
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            cardDesign(
                painter = painterResource(id = firstResourceID),
                contentDescription = firstName,
                name = firstName,
                modifier = Modifier
                    .weight(1F),
                onClick = { intentPass(id = firstIntentValue) }
            )
            cardDesign(
                painter = painterResource(id = secondResourceID),
                contentDescription = secondName,
                name = secondName,
                modifier = Modifier
                    .weight(1f),
                onClick = { intentPass(id = secondIntentValue) }

            )
        }
    }

    private fun intentPass(id: String) {
        val iNext = Intent(applicationContext, WebViewActivity::class.java)
        iNext.putExtra("id", id)
        startActivity(iNext)
    }
}

