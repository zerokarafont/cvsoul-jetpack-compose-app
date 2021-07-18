package com.compose.cvsoul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compose.cvsoul.ui.theme.CVSoulTheme
import com.compose.cvsoul.viewmodel.TestViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CVSoulTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }

    companion object {
        init {
            System.loadLibrary("spec")
        }
    }
}

@Composable
fun Greeting(name: String, viewModel: TestViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    
    val scope = rememberCoroutineScope()

    Column {
        Button(onClick = {
            scope.launch {
                viewModel.testLogin()
            }
        }) {
            Text(text = "测试post")
        }
        Button(onClick = {
            scope.launch {
                viewModel.testGet()
            }
        }) {
            Text(text = "测试get")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CVSoulTheme {
        Greeting("Android")
    }
}