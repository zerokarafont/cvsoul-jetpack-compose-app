package com.compose.cvsoul.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CustomTopAppBar(
    navController: NavController,
    onExpand: () -> Unit
) {

    fun jumpToSearch() { navController.navigate(route = "search") { launchSingleTop = true } }

    TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp, modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(56.dp)
        .padding(vertical = 10.dp)) {
            Row {
                IconButton(onClick = onExpand ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "菜单")
                }
            }
            Search(onSearch = {}, onTap = { jumpToSearch() })
    }
}