package com.stefita.astronautsdb.ui

import AstronautsDbTheme
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.stefita.astronautsdb.presentation.R
import com.stefita.astronautsdb.ui.navigation.AstronautDetails
import com.stefita.astronautsdb.ui.navigation.AstronautsDestination
import com.stefita.astronautsdb.ui.navigation.AstronautsList
import com.stefita.astronautsdb.ui.navigation.AstronautsNavHost
import com.stefita.astronautsdb.ui.navigation.astronautsDbScreens

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AstronautsDbApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AstronautsDbApp() {
    AstronautsDbTheme {
        val navController = rememberAnimatedNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            astronautsDbScreens.find { currentDestination?.route?.contains(it.route) ?: false }
                ?: AstronautsList

        Scaffold(
            topBar = {
                AstronautsDbAppBar(
                    currentScreen = currentScreen,
                    onBackClicked = { navController.popBackStack() }
                )
            }
        ) { innerPadding ->
            AstronautsNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstronautsDbAppBar(
    currentScreen: AstronautsDestination,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = currentScreen.screenTitle) },
        navigationIcon = {
            Box(modifier = Modifier.size(56.dp) ) {
                if (currentScreen.route == AstronautDetails.route) {
                    IconButton(onClick = onBackClicked,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back")
                    }
                } else {
                    Image(
                        painterResource(id = R.mipmap.ic_launcher_foreground),
                        contentDescription = "logo",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview(name = "Home Preview")
@Composable
fun HomePreview() {
    AstronautsDbApp()
}

@Preview(name = "Top Bar", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AstronautsDbAppBarPreview() {
    AstronautsDbAppBar(currentScreen = AstronautsList, onBackClicked = {})
}