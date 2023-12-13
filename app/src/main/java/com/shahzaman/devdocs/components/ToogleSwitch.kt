package com.soloftech.keyboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToggleSwitch(
    isChecked: Boolean,
    text: String,
    description: String = "",
    onCheck: (Boolean) -> Unit
) {
    var switch by remember {
        mutableStateOf(
            ToggleableInfo(
                isChecked = isChecked,
                text = text,
                description = description
            )
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(4f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = switch.text,
                style = MaterialTheme.typography.titleMedium
            )
            if (switch.description.isNotEmpty()) {
                Text(
                    text = switch.description,
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 13.sp,
                    fontWeight = FontWeight(450)
                )
            }
        }
        Switch(
            modifier = Modifier.weight(1f),
            checked = switch.isChecked,
            onCheckedChange = { isChecked ->
                switch = switch.copy(isChecked = isChecked)
                onCheck.invoke(isChecked)
            },
        )
    }
}

data class ToggleableInfo(
    val isChecked: Boolean,
    val text: String,
    val description: String,
)