package com.awais.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    validator: ((String) -> String)? = null,
    hintText: String = "",
    labelText: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val obscure = remember { mutableStateOf(isPassword) }
    val errorText = remember { mutableStateOf("") }
    
    Column {
        if (labelText.isNotEmpty()) {
            Text(
                text = labelText,
                style = textStyle.copy(
                    fontWeight = FontWeight(700),
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = modifier.padding(bottom = 8.dp)
            )
        }
        BasicTextField(
            enabled = enabled,
            value = value,
            onValueChange = {
                onValueChange(it)
                
                if (validator == null) {
                    errorText.value = ""
                    return@BasicTextField
                }
                
                errorText.value = validator(it)
                
            },
            textStyle = textStyle,
            maxLines = maxLines,
            visualTransformation = if (obscure.value) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = if (errorText.value.isEmpty())
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Red,
                    shape = MaterialTheme.shapes.medium
                )
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                )
                .padding(horizontal = 16.dp, vertical = 16.dp),
            decorationBox = { innerTextField ->
                Row {
                    leadingIcon?.invoke()
                    if (leadingIcon != null) Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = hintText,
                                style = textStyle,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = modifier
                            )
                        }
                        innerTextField()
                    }
                    if (trailingIcon != null || isPassword) Spacer(modifier = Modifier.width(8.dp))
                    trailingIcon?.invoke()
                    
                    if (isPassword)
                        Box(modifier = Modifier.clickable { obscure.value = !obscure.value }) {
                            Icon(
                                imageVector = if (obscure.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                                contentDescription = "Person icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                }
            }
        )
        if (errorText.value.isNotEmpty()) {
            Text(
                text = errorText.value,
                color = Color.Red
            )
        }
    }
}

