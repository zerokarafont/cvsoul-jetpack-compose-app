package com.compose.cvsoul.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.cvsoul.ui.component.Search

@Composable
fun SearchScreen(navController: NavController) {

    fun handleBackHome() { navController.navigate(route = "main") }

    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        IconButton(onClick = { handleBackHome() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
        }
        Search(onSearch = { /*TODO*/ }, onTap = {})
    }
}