package ca.qc.cstj.bottomnavigation.ui.navigation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ca.qc.cstj.bottomnavigation.ui.navigation.core.screen
import ca.qc.cstj.bottomnavigation.ui.navigation.main.components.MainBottomBar
import ca.qc.cstj.bottomnavigation.ui.navigation.main.components.MainTopBar
import ca.qc.cstj.bottomnavigation.ui.screens.NavGraphs
import ca.qc.cstj.bottomnavigation.ui.screens.appCurrentDestinationAsState
import ca.qc.cstj.bottomnavigation.ui.screens.startAppDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate
import kotlinx.coroutines.CoroutineScope

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {

    val navController = rememberNavController()

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    //TODO : onMainScreenEvent Callback (lambda)

    //TODO : currentScreen definition
    //(gauche) ?: (droite) Si la partie de gauche est nulle la partie de droite est retournée
    val currentScreen = navController.appCurrentDestinationAsState().value?.screen
        ?: NavGraphs.root.startAppDestination.screen

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.secondaryContainer,
                        MaterialTheme.colorScheme.primary
                    )
                )
            ),
        topBar = {
            if(currentScreen.isTopBarVisible) {
                MainTopBar(
                    currentScreen = currentScreen,
                    titleFormatArgs = listOf("").toTypedArray()
                ) {
                    navController.navigateUp()
                }
            }
        },
        bottomBar = {
            if(currentScreen.isBottomBarVisible) {
                MainBottomBar(
                    currentScreen = currentScreen,
                    onNavigate = { destination ->
                        navController.navigate(destination)
                    }
                )
            }
        },
        snackbarHost = {

        },
        containerColor = Color.Transparent
    ) { innerPaddings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navController = navController,
            navGraph = NavGraphs.root
        )
    }
}