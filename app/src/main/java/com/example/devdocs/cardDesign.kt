package com.example.devdocs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun cardDesign(
    modifier: Modifier = Modifier,
    name: String,
    contentDescription: String,
    painter: Painter,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .clickable { onClick() }
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxHeight(0.7f)
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )
            Text(
                text = name,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun View() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            cardDesign(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "Compose",
                name = "Compose",
                modifier = Modifier
                    .weight(1f),
                onClick = { Unit }
            )
            cardDesign(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Android",
                name = "Android",
                modifier = Modifier
                    .weight(1f),
                onClick = { Unit }
            )
        }
    }
}
