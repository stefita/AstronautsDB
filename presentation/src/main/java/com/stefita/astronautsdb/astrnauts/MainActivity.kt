package com.stefita.astronautsdb.astrnauts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

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
fun Home() {
    MaterialTheme {
        Surface {
            Text(text = "Test!")
        }
    }
}

@Preview(name = "Home Preview")
@Composable
fun HomePreview() {
    Home()
}