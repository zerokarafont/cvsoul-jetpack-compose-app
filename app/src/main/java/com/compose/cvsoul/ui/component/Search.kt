package com.compose.cvsoul.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Search(onSearch: (input: String) -> Unit, onTap: () -> Unit, initIsFocus: Boolean = false) {
    var value by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value

    fun handleSearch() {
        onSearch(value)
        focusManager.clearFocus()
    }

    LaunchedEffect(Unit) {
        if (initIsFocus) {
           focusRequester.requestFocus()
        }
    }

    LaunchedEffect(isFocused) {
        if (isFocused) {
            onTap()
        }
    }

    Box(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = { value = it },
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                color = Color.Gray,
                textAlign = TextAlign.Start,
            ),
            cursorBrush = SolidColor(Color.Gray),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { if (value.isNotEmpty()) handleSearch() }),
            interactionSource = interactionSource,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White, RoundedCornerShape(28.dp))
                .focusRequester(focusRequester)
//                .focusable(enabled = initIsFocus, interactionSource = interactionSource)
            ,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    IconButton(
                        onClick = {},
                        enabled = false,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            tint = Color.Gray,
                            contentDescription = "搜索"
                        )
                    }
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        innerTextField()
                    }
                    if (value.isNotEmpty()) {
                        IconButton(
                            onClick = { value = "" },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                tint = Color.Gray,
                                contentDescription = "关闭"
                            )
                        }
                    }
                }
            }
        )
    }
}