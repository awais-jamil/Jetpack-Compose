package com.awais.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    loading: Boolean = false,
    content: @Composable () -> Unit = {},
) {
    Button(
        onClick = {
            if (!loading) {
                onClick()
            }
        },
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            content()
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable () -> Unit = {},
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = modifier
    ) {
        content()
    }
}

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    title: String = "",
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick, enabled = enabled)
    ) {
        Text(text = title, color = MaterialTheme.colorScheme.primary)
    }
}