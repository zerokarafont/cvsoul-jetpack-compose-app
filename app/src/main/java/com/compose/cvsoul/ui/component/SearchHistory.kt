package com.compose.cvsoul.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.cvsoul.util.clearHistory
import com.compose.cvsoul.util.getHistory

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun SearchHistory(onTap: (item: String) -> Unit) {
    var history by remember { mutableStateOf(setOf("")) }

    DisposableEffect(Unit) {
        history = getHistory()

        onDispose { }
    }

    if (history.isNotEmpty()) {
        Surface(
            color = Color.White,
            contentColor = Color(0xFFEEEEEE),
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(120.dp)
        ) {
            Text(
                text = "搜索历史", 
                color = Color.Black,  
                fontWeight = FontWeight(700), 
                modifier = Modifier
                    .padding(start = 2.dp, bottom = 2.dp)
                    .padding(vertical = 5.dp)
            )
            Column(modifier = Modifier.padding(top = 25.dp)) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                ) {
                    history.toList().forEach { item ->
                        item {
                            Card(
                                elevation = 0.dp,
                                backgroundColor = Color(0xAAEEEEEE),
                                contentColor = Color(0xFFAAAAAA),
                                modifier = Modifier.padding(horizontal = 2.dp, vertical = 5.dp),
                                onClick = { onTap(item) }) {
                                Text(text = item, textAlign = TextAlign.Center, modifier = Modifier.padding(vertical = 5.dp))
                            }
                        }
                    }
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                clearHistory()
                history = setOf()
            }) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "")
            }
                Text(text = "清空历史记录", color = Color(0xFFAAAAAA))
        }
    }
}