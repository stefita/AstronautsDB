package com.stefita.astronautsdb.ui.astronautslist

import AstronautsDbTheme
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stefita.astronautsdb.entities.AgencySource
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.astronautsdb.presentation.R
import com.stefita.astronautsdb.ui.common.AstronautImage
import com.stefita.astronautsdb.ui.theme.Gainsboro
import org.koin.androidx.compose.koinViewModel

@Composable
fun AstronautsListScreen(
    listState: LazyListState,
    gridState: LazyGridState,
    viewModel: AstronautsViewModel = koinViewModel(),
    onAstronautClicked: (Int) -> Unit
) {
    val astronautsList = viewModel.astronautFlow.collectAsLazyPagingItems()

    BoxWithConstraints {
        if (maxWidth < 480.dp) {
            AstronautListAsColumn(
                astronauts = astronautsList,
                listState = listState,
                onAstronautClicked = onAstronautClicked
            )
        } else {
            AstronautsListAsGrid(
                astronauts = astronautsList,
                gridState = gridState,
                onAstronautClicked = onAstronautClicked
            )
        }
    }
}

@Composable
fun AstronautListAsColumn(
    astronauts: LazyPagingItems<AstronautSource>,
    listState: LazyListState,
    onAstronautClicked: (Int) -> Unit
) {
    if (astronauts.itemCount == 0) {
        if (astronauts.loadState.source.prepend != LoadState.NotLoading(false)) {
            EmptyListView()
        }
    } else {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(astronauts.itemCount) { index ->
                astronauts[index]?.let {
                    AddItemToList(
                        astronaut = it,
                        cellType = CellType.List,
                        onAstronautClicked = onAstronautClicked
                    )
                }
            }

            when (astronauts.loadState.refresh) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator()
                        LoadingScreen()
                    }
                }

                is LoadState.Error -> {
                    item {
                        EmptyListView()
                    }
                }
            }

            when (astronauts.loadState.append) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> {
                    item {
                        LoadingItem(CellType.List)
                    }
                }

                is LoadState.Error -> {
                    item {
                        ErrorItem(
                            errorMessage = (astronauts.loadState.append as LoadState.Error).error.message,
                            cellType = CellType.List
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyListView() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.list_empty_title),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = stringResource(id = R.string.list_empty_body),
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun LoadingItem(cellType: CellType) {
    Card(
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        if (cellType is CellType.List) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                LoadingItemColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        } else {
            LoadingItemColumn(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .height(128.dp)
            )
        }
    }
}

@Composable
fun ErrorItem(errorMessage: String?, cellType: CellType) {
    Card(
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        if (cellType is CellType.List) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ErrorContentView(errorMessage)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .height(128.dp)
            ) {
                ErrorContentView(errorMessage)
            }
        }
    }
}

@Composable
fun ErrorContentView(errorMessage: String? = null) {
    Text(
        text = stringResource(id = R.string.list_error_title),
        style = MaterialTheme.typography.headlineSmall
    )
    Text(
        text = errorMessage ?: stringResource(id = R.string.list_error_body_generic),
        modifier = Modifier.padding(top = 8.dp),
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun LoadingItemColumn(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = Gainsboro)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstronautCell(
    astronautSource: AstronautSource,
    cellType: CellType,
    onAstronautClicked: (Int) -> Unit
) {
    Card(
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),
        onClick = { onAstronautClicked(astronautSource.id!!) }
    ) {
        when (cellType) {
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
fun AstronautsListAsGrid(
    astronauts: LazyPagingItems<AstronautSource>,
    gridState: LazyGridState,
    onAstronautClicked: (Int) -> Unit
) {
    if (astronauts.itemCount == 0) {
        if (astronauts.loadState.source.prepend != LoadState.NotLoading(false)) {
            EmptyListView()
        }
    } else {
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Adaptive(148.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(astronauts.itemCount) { index ->
                astronauts[index]?.let {
                    AstronautCell(
                        astronautSource = it,
                        cellType = CellType.Grid,
                        onAstronautClicked = onAstronautClicked
                    )
                }
            }

            when (astronauts.loadState.refresh) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator()
                        LoadingScreen()
                    }
                }

                is LoadState.Error -> {
                    item {
                        EmptyListView()
                    }
                }
            }

            when (astronauts.loadState.append) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> {
                    item {
                        LoadingItem(CellType.Grid)
                    }
                }

                is LoadState.Error -> {
                    item {
                        ErrorItem(
                            errorMessage = (astronauts.loadState.append as LoadState.Error).error.message,
                            cellType = CellType.Grid
                        )
                    }
                }
            }
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
            NameText(
                astronaut = astronaut,
                maxLines = 1,
            )

            AgencyText(astronaut = astronaut)
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
            .height(140.dp)
    ) {
        AstronautProfileThumb(imgUrl = astronaut.profileImageThumbnail)

        NameText(
            astronaut = astronaut,
            maxLines = 2,
            textAlign = TextAlign.Center,
            // modifier = Modifier.weight(1f, fill = true)
        )

        AgencyText(astronaut = astronaut, textAlign = TextAlign.Center)
    }
}

@Composable
fun NameText(
    astronaut: AstronautSource,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
    modifier: Modifier = Modifier
) {
    astronaut.name?.let {
        Text(
            text = it,
            maxLines = maxLines,
            textAlign = textAlign,
            style = MaterialTheme.typography.headlineSmall,
            modifier = modifier.padding(bottom = 4.dp)
        )
    }
}

@Composable
fun AgencyText(astronaut: AstronautSource, textAlign: TextAlign? = null) {
    astronaut.agency?.name?.let {
        Text(
            text = it,
            textAlign = textAlign,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AddItemToList(
    astronaut: AstronautSource,
    cellType: CellType,
    onAstronautClicked: (Int) -> Unit
) {
    AstronautCell(
        astronautSource = astronaut,
        cellType = cellType,
        onAstronautClicked = onAstronautClicked
    )
}

@Composable
fun AstronautProfileThumb(imgUrl: String?) {
    AstronautImage(
        imageUrl = imgUrl,
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
    )
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (img, progress) = createRefs()

            Image(
                painter = painterResource(R.mipmap.ic_launcher_foreground),
                contentDescription = "Loading",
                modifier = Modifier.constrainAs(img) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            CircularProgressIndicator(
                color = Gainsboro,
                modifier = Modifier
                    .size(108.dp)
                    .constrainAs(progress) {
                        top.linkTo(img.top)
                        bottom.linkTo(img.bottom)
                        start.linkTo(img.start)
                        end.linkTo(img.end)
                    }
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AstronautRowPreview() {
    AstronautsDbTheme {
        val astronaut = AstronautSource(
            id = 12,
            name = "Alexander Gerst",
            agency = AgencySource(
                abbr = "NASA",
                administrator = "Michael Moore",
                countryCode = "USA",
                description = "Lorem ipsum",
                foundingYear = "1930",
                id = 12,
                imgUrl = "",
                logoUrl = "",
                name = "NASA",
                type = "Government",
                url = ""
            ),
            profileImageThumbnail = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/astronaut_images/alexander_gerst_thumbnail_20220911034407.jpeg"
        )

        AstronautCell(
            astronautSource = astronaut,
            cellType = CellType.Grid,
            onAstronautClicked = {})
    }
}

sealed class CellType {
    object List : CellType()
    object Grid : CellType()
}