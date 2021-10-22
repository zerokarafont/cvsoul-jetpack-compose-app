package com.compose.cvsoul.ui.screen

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.compose.cvsoul.viewmodel.QuoteDetailViewModel

@Composable
fun QuoteDetailScreen(navController: NavController, viewModel: QuoteDetailViewModel,  _id: String) {
    val data by viewModel.data.observeAsState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(_id) {
        if (_id.isNotEmpty()) {
            viewModel.fetchQuotePlaylistDetail(_id)
        }
    }

    Surface {
        Text(text = "Ceshi")
    }
}