package com.compose.cvsoul.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.cvsoul.repository.model.TagModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Tag(data: List<TagModel?>? = null, onTap: (tag: String) -> Unit ) {
    Surface(
        color = Color.White,
        contentColor = Color(0xFFEEEEEE),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
    ) {
        Text(text = "风格", color = Color.Black,  fontWeight = FontWeight(700), modifier = Modifier.padding(start = 2.dp).padding(vertical = 5.dp))
        Column(modifier = Modifier.padding(top = 25.dp)) {
            data?.let {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                ) {
                    items(data.size) { index ->
                        val item = data[index]
                        item?.let {
                            Card(
                                elevation = 0.dp,
                                backgroundColor = Color(0xAAEEEEEE),
                                contentColor = Color(0xFFAAAAAA),
                                modifier = Modifier.padding(horizontal = 2.dp, vertical = 5.dp),
                                onClick = { onTap(item.name) }) {
                                Text(text = item.name, textAlign = TextAlign.Center, modifier = Modifier.padding(vertical = 5.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}