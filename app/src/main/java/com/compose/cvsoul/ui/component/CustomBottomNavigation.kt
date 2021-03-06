package com.compose.cvsoul.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptions


@Composable
fun CustomBottomNavigation(navController: NavController) {

    var selectedItem by remember { mutableStateOf(0) }
    val icons = mutableListOf(
        mapOf("iconName" to "chatroom", "desc" to "聊天室"),
        mapOf("iconName" to "quote", "desc" to "语录"),
        mapOf("iconName" to "person", "desc" to "消息")
    )

    BottomNavigation(
        backgroundColor = Color.White,
    ) {
        icons.forEachIndexed { index, icon ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource(),
                        onClick = {
                            selectedItem = index
                            navController.navigate(route = icon["iconName"]!!) {
                                popUpTo(route = icon["iconName"]!!) { inclusive = false }
                            }
                        }
                    )
            ) {
                when(icon["iconName"]) {
                    "chatroom" -> {
                        if (index == selectedItem)
                            Icon(imageVector = Icons.Filled.Home, contentDescription = icon["desc"])
                        else
                            Icon(imageVector = Icons.Outlined.Home, contentDescription = icon["desc"])
                    }
                    "quote" -> {
                        if (index == selectedItem)
                            Icon(imageVector = Icons.Filled.Call, contentDescription = icon["desc"])
                        else
                            Icon(imageVector = Icons.Outlined.Call, contentDescription = icon["desc"])
                    }
                    "person" -> {
                        if (index == selectedItem)
                            Icon(imageVector = Icons.Filled.Person, contentDescription = icon["desc"])
                        else
                            Icon(imageVector = Icons.Outlined.Person, contentDescription = icon["desc"])
                    }
                }
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = icon["desc"]!!, fontSize = 10.sp)
            }
        }
    }
}
