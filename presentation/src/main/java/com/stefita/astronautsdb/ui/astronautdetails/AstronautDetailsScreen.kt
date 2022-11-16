package com.stefita.astronautsdb.ui.astronautdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.stefita.astronautsdb.ui.theme.Gainsboro
import org.koin.androidx.compose.koinViewModel

@Composable
fun AstronautDetailsScreen(
    astronautDetailsViewModel: AstronautDetailsViewModel = koinViewModel(),
    astronautId: Int
) {
    val astronautState by astronautDetailsViewModel.state.observeAsState()

    // Explore compose side effects. Launch viewlmodel method when id changes
    LaunchedEffect(astronautId) {
        astronautDetailsViewModel.loadDetail(astronautId)
    }

    astronautState?.let { state ->
        when (state) {
            is AstronautDetailsViewModel.DetailState.Success -> {
                Text(text = state.astronaut.name, color = Gainsboro)
            }
            else -> {
                Text(text = "something went wrong")
            }
        }
    }
}