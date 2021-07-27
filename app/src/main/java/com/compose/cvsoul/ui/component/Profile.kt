package com.compose.cvsoul.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.cvsoul.repository.model.UserModel
import com.compose.cvsoul.util.crypto.getToken

@Composable
fun Profile(navController: NavController) {

    fun handleNavigate(route: String) {
        navController.navigate(route = route)
    }

    fun checkIsAlreadyLogin(): Boolean {
        return getToken()?.isNotEmpty() ?: false
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xCCEEEEEE))) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            AvatarItem(onClick = { handleNavigate("auth") })
            Spacer(modifier = Modifier.height(20.dp))
            ProfileItem(text = "我的收藏", icon = Icons.Filled.Star, route = "star", onClick = { it -> handleNavigate(it) })
            ProfileItem(text = "关于", icon = Icons.Filled.Info, route = "about", onClick = { it -> handleNavigate(it) })
            Spacer(modifier = Modifier.height(20.dp))
            if (checkIsAlreadyLogin()) {
                LogoutItem()
            }
        }
    }
}

@Composable
fun AvatarItem(userInfo: UserModel? = null, onClick: () -> Unit) {

    fun handleLogin() {
        onClick()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            border = BorderStroke(5.dp, Color.White)
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        if (userInfo == null) {
            Row(Modifier.clickable { handleLogin() }) {
                Text(text = "立即登录")
                Image(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
            }
        } else {
            Row {
                Text(text = userInfo.username)
            }
        }
    }
}

@Composable
fun ProfileItem(text: String, icon: ImageVector, route: String, onClick: (dest: String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Color.White)
            .padding(horizontal = 10.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = { onClick(route) }
            )
    ) {
        Image(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = text)
    }
    Divider(color = Color.Gray, thickness = Dp.Hairline)
}

@Composable
fun LogoutItem() {
    fun handleLogout() {

    }

    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Red),
        onClick = { handleLogout() },
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
    ) {
        Text(text = "退出登录")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfilePreview() {
//    Profile()
//}