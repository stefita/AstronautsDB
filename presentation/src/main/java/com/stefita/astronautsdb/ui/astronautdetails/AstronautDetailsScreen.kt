package com.stefita.astronautsdb.ui.astronautdetails

import AstronautsDbTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.astronautsdb.ui.theme.RichBlack
import com.stefita.astronautsdb.ui.theme.RichBlack29Alpha
import org.koin.androidx.compose.koinViewModel

// TODO Show more data
// TODO screen scrollable
@Composable
fun AstronautDetailsScreen(
    astronautDetailsViewModel: AstronautDetailsViewModel = koinViewModel(),
    astronautId: Int
) {
    val astronautState by astronautDetailsViewModel.state.observeAsState()

    // Explore compose side effects. Launch viewmodel method when id changes
    LaunchedEffect(astronautId) {
        astronautDetailsViewModel.loadDetail(astronautId)
    }

    astronautState?.let { state ->
        when (state) {
            is AstronautDetailsViewModel.DetailState.Success -> {
                AstronautDetailsCard(astronaut = state.astronaut)
            }

            else -> {
                // TODO
                Text(text = "something went wrong")
            }
        }
    }
}

@Composable
fun AstronautDetailsCard(astronaut: AstronautSource, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        BoxWithConstraints {
            if (maxWidth < 480.dp) {
                AstronautDetailsCardSmallMedium(astronaut)
            } else {
                AstronautDetailsCardLarge(astronaut)
            }
        }
    }
}

@Composable
fun AstronautDetailsCardSmallMedium(astronaut: AstronautSource) {
    Column {
        ProfilePictureSmallMedium(imgUrl = astronaut.profileImage, name = astronaut.name)

        DetailsCard(astronaut = astronaut)
    }
}

@Composable
fun AstronautDetailsCardLarge(astronaut: AstronautSource) {
    Row(modifier = Modifier.fillMaxHeight()) {
        ProfilePictureLarge(imgUrl = astronaut.profileImage, name = astronaut.name)

        DetailsCard(astronaut = astronaut)
    }
}

@Composable
fun DetailsCard(astronaut: AstronautSource) {
    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = RichBlack),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Bio",
            modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = astronaut.bio,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ProfilePictureSmallMedium(imgUrl: String, name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imgUrl)
                .memoryCacheKey(imgUrl)
                .diskCacheKey(imgUrl)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
        )

        ElevatedCard(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = RichBlack29Alpha),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        }
    }
}

@Composable
fun ProfilePictureLarge(imgUrl: String, name: String) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxWidth(0.3f)
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUrl)
                    .memoryCacheKey(imgUrl)
                    .diskCacheKey(imgUrl)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            )

            ElevatedCard(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = RichBlack29Alpha),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfilePicturePreview() {
    AstronautsDbTheme {
        ProfilePictureSmallMedium(
            imgUrl = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/astronaut_images/claude2520nicollier_image_20181127203218.jpg",
            name = "Claude Nicollier"
        )
    }
}