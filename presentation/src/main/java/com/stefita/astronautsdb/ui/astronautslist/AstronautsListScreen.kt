package com.stefita.astronautsdb.ui.astronautslist

import AstronautsDbTheme
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stefita.astronautsdb.entities.Agency
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.astronautsdb.presentation.R
import com.stefita.astronautsdb.ui.AstronautsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AstronautsListScreen(
    viewModel: AstronautsViewModel = koinViewModel(),
    onAstronautClicked: (Int) -> Unit
) {
    val state by viewModel.state.observeAsState()

    when (state) {
        is AstronautsViewModel.ListState.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items((state as AstronautsViewModel.ListState.Success).astronauts) {
                    AstronautRow(astronautSource = it, onAstronautClicked = {})
                }
            }
        }

        else -> {
            // Do nothing
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstronautRow(astronautSource: AstronautSource, onAstronautClicked: (Int) -> Unit) {
    Card(
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp, pressedElevation = 8.dp),
        onClick = { onAstronautClicked(astronautSource.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(astronautSource.profileImageThumbnail)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_astronaut),
                contentDescription = "test",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )

            Column(
                Modifier
                    .weight(1f, fill = true)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = astronautSource.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Flights count: ${astronautSource.flightsCount}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }

    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AstronautRowPreview() {
    AstronautsDbTheme {
        val astronaut = AstronautSource(
            id = 12,
            name = "Alexander Gerst",
            agency = Agency(
                abbrev = "NASA",
                administrator = "Michael Moore",
                countryCode = "USA",
                description = "Lorem ipsum",
                foundingYear = "1930",
                id = 12,
                imageUrl = "",
                logoUrl = "",
                name = "NASA",
                type = "Government",
                url = ""
            ),
            profileImageThumbnail = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/astronaut_images/alexander_gerst_thumbnail_20220911034407.jpeg"
        )

        AstronautRow(astronautSource = astronaut, onAstronautClicked = {})
    }
}