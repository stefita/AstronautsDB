package com.stefita.astronautsdb.ui.astronautslist

import AstronautsDbTheme
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stefita.astronautsdb.entities.Agency
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.astronautsdb.presentation.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun AstronautsListScreen(
    viewModel: AstronautsViewModel = koinViewModel(),
    onAstronautClicked: (Int) -> Unit
) {

    // Use viewmodel init to load list data
    val state by viewModel.state.observeAsState()

    when (state) {
        is AstronautsViewModel.ListState.Success -> {
            BoxWithConstraints {

                if (maxWidth < 480.dp) {
                    AstronautListAsColumn(
                        astronauts = (state as AstronautsViewModel.ListState.Success).astronauts,
                        onAstronautClicked = onAstronautClicked
                    )
                } else {
                    AstronautsListAsGrid(
                        astronauts = (state as AstronautsViewModel.ListState.Success).astronauts,
                        onAstronautClicked = onAstronautClicked
                    )
                }
            }

        }

        else -> {
            // Do nothing
        }
    }
}

@Composable
fun AstronautListAsColumn(astronauts: List<AstronautSource>, onAstronautClicked: (Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(astronauts) {
            AstronautCell(astronautSource = it, cellType = CellType.List, onAstronautClicked = onAstronautClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstronautCell(astronautSource: AstronautSource, cellType: CellType, onAstronautClicked: (Int) -> Unit) {
    Card(
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),
        onClick = { onAstronautClicked(astronautSource.id) }
    ) {
        when(cellType) {
            is CellType.List -> {
                AstronautListCell(astronaut = astronautSource)
            }
            is CellType.Grid -> {
                AstronautGridCell(astronaut = astronautSource)
            }
        }
    }
}

@Composable
fun AstronautsListAsGrid(astronauts: List<AstronautSource>, onAstronautClicked: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(148.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(astronauts) {
            AstronautCell(astronautSource = it, cellType = CellType.Grid, onAstronautClicked = onAstronautClicked)
        }
    }
}

@Composable
fun AstronautListCell(astronaut: AstronautSource) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        AstronautProfileThumb(imgUrl = astronaut.profileImageThumbnail)

        Column(
            Modifier
                .weight(1f, fill = true)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = astronaut.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Flights #${astronaut.flightsCount}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Composable
fun AstronautGridCell(astronaut: AstronautSource) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(128.dp)
    ) {
        AstronautProfileThumb(imgUrl = astronaut.profileImageThumbnail)

        Text(
            text = astronaut.name,
            maxLines = 2,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.weight(1f, fill = true)
        )
        Text(
            text = "Flights #${astronaut.flightsCount}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AstronautProfileThumb(imgUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.mipmap.ic_launcher_foreground),
        contentDescription = "test",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
    )
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

        AstronautCell(astronautSource = astronaut, cellType = CellType.Grid, onAstronautClicked = {})
    }
}

sealed class CellType {
    object List : CellType()
    object Grid : CellType()
}