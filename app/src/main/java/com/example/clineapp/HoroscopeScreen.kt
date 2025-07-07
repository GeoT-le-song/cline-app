package com.example.clineapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoroscopeScreen(
    viewModel: HoroscopeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("今日の運勢") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is HoroscopeUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is HoroscopeUiState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("エラーが発生しました。")
                        Button(onClick = { viewModel.fetchHoroscope() }) {
                            Text("リトライ")
                        }
                    }
                }
                is HoroscopeUiState.Success -> {
                    HoroscopeDetail(horoscope = state.horoscope)
                }
            }
        }
    }
}

@Composable
fun HoroscopeDetail(horoscope: HoroscopeDetailDto) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "${horoscope.rank}位 ${horoscope.sign}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = horoscope.content)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "ラッキーアイテム: ${horoscope.item}")
        Text(text = "ラッキーカラー: ${horoscope.color}")
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = "総合: ${horoscope.total}")
            Text(text = "恋愛: ${horoscope.love}")
            Text(text = "金運: ${horoscope.money}")
            Text(text = "仕事: ${horoscope.job}")
        }
    }
}
