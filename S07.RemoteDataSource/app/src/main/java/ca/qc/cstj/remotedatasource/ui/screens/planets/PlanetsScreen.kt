package ca.qc.cstj.remotedatasource.ui.screens.planets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ca.qc.cstj.remotedatasource.ui.composables.ErrorMessage
import ca.qc.cstj.remotedatasource.ui.composables.LoadingAnimation
import ca.qc.cstj.remotedatasource.ui.screens.destinations.DetailsPlanetScreenDestination
import ca.qc.cstj.remotedatasource.ui.screens.destinations.DetailsPlanetScreenV2Destination
import ca.qc.cstj.remotedatasource.ui.screens.planets.components.PlanetCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate

@RootNavGraph(start = true)
@Destination
@Composable
fun PlanetsScreen(
    navController: NavController,
    viewModel : PlanetsViewModel = viewModel()
) {
    when(val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        is PlanetsUiState.Error -> {
            ErrorMessage(ex = uiState.ex)
        }
        PlanetsUiState.Loading -> {
            LoadingAnimation()
        }
        is PlanetsUiState.Success -> {
            LazyColumn {
                items(uiState.planets) {
                    PlanetCard(it, onClick = { planet ->
                        //V1: navController.navigate(DetailsPlanetScreenDestination(planet))
                        //V2
                        navController.navigate(DetailsPlanetScreenV2Destination(planet.href))
                    })
                }
            }
        }
    }


}