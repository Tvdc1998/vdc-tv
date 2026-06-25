package com.vdc.tv.setup.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun PinCodeDialog(
    onDismissRequest: () -> Unit,
    onPinSubmit: (String) -> Unit,
    error: String? = null
) {
    var pin by remember { mutableStateOf("") }
    val focusRequesters = remember { List(4) { FocusRequester() } }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(24.dp).width(400.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Voer pincode in",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(4) { index ->
                        val char = if (index < pin.length) "•" else ""
                        val isFocused = index == pin.length.coerceAtMost(3)
                        
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .border(
                                    width = if (isFocused) 3.dp else 1.dp,
                                    color = if (isFocused) MaterialTheme.colorScheme.primary else Color.Gray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .focusRequester(focusRequesters[index]),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                if (error != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = error, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }

                // Invisible TextField to capture input
                BasicTextField(
                    value = pin,
                    onValueChange = {
                        if (it.length <= 4 && it.all { char -> char.isDigit() }) {
                            pin = it
                            if (it.length == 4) {
                                onPinSubmit(it)
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    modifier = Modifier
                        .size(0.dp)
                        .focusRequester(focusRequesters[0])
                        .onKeyEvent { event ->
                            if (event.key == Key.Back && pin.isNotEmpty()) {
                                pin = pin.dropLast(1)
                                true
                            } else {
                                false
                            }
                        }
                )

                LaunchedEffect(Unit) {
                    focusRequesters[0].requestFocus()
                }
            }
        }
    }
}
