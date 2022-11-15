package com.stefita.astronautsdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Home()
        }
    }
}

@Composable
fun Home(viewModel: AstronautsViewModel = koinViewModel()) {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val state by viewModel.state.observeAsState()
            when (state) {
                is AstronautsViewModel.ListState.Success -> {
                    LazyColumn {
                        items((state as AstronautsViewModel.ListState.Success).astronauts) {
                            Text(text = it.name, color = MaterialTheme.colorScheme.onBackground)
                        }
                    }
                }
                else -> {
                    // Do nothing
                }
            }
        }
    }
}

@Preview(name = "Home Preview")
@Composable
fun HomePreview() {
    Home()
}