package ca.qc.cstj.bottomnavigation.ui.navigation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ca.qc.cstj.bottomnavigation.ui.NavGraphs
import ca.qc.cstj.bottomnavigation.ui.appCurrentDestinationAsState
import ca.qc.cstj.bottomnavigation.ui.navigation.core.screen
import ca.qc.cstj.bottomnavigation.ui.navigation.main.components.MainBottomBar
import ca.qc.cstj.bottomnavigation.ui.navigation.main.components.MainTopBar
import ca.qc.cstj.bottomnavigation.ui.startAppDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {


    val navController = rememberNavController()

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    //onMainScreenEvent Callback (lambda)
    val onMainScreenEvent : (MainViewModel.ScreenEvent.In) -> Unit = { viewModel.onEvent(it) }

    //currentScreen definition
    //(gauche) ?: (droite) Si la partie de gauche est nulle la partie de droite est retournée
    val currentScreen = navController.appCurrentDestinationAsState().value?.screen
        ?: NavGraphs.secondLevel.startAppDestination.screen


    LaunchedEffect(true) {
        viewModel.eventChannel.collectLatest {
            when(it) {
                is MainViewModel.ScreenEvent.Out.ShowSnackbar -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message = it.message)
                    }
                }
            }
        }
    }

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
                    titleFormatArgs = uiState.value.titleFormatArgs
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
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(snackbarData = it)
            }
        },
        containerColor = Color.Transparent
    ) { innerPaddings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navController = navController,
            navGraph = NavGraphs.secondLevel,
            dependenciesContainerBuilder = {
                dependency(onMainScreenEvent)
            }
        )
    }
}