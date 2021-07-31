package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.cvsoul.ui.component.Search

@Composable
fun SearchScreen(navController: NavController) {

    fun handleBack() { navController.popBackStack() }

    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFFEEEEEE))) {
        Row(
            modifier = Modifier
                .requiredHeight(56.dp)
                .padding(vertical = 10.dp)
        ) {
            IconButton(onClick = { handleBack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
            Search(onSearch = { /*TODO*/ }, onTap = {}, initIsFocus = true)
        }
    }
}