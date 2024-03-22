package ca.qc.cstj.remotedatasource.ui.screens.planets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.remotedatasource.ui.screens.planets.components.PlanetCard

@Composable
fun PlanetsScreen(viewModel : PlanetsViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LazyColumn {
        items(uiState.planets) {
            PlanetCard(it)
        }
    }
}