package ca.qc.cstj.remotedatasource.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.remotedatasource.models.Planet
import ca.qc.cstj.remotedatasource.ui.screens.planets.components.PlanetCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph //Si absent la destination est dans le graphe root (start = false) par défault
@Destination
@Composable
fun DetailsPlanetScreen(planet: Planet) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        PlanetCard(planet = planet)
    }
}

@RootNavGraph
@Destination
@Composable
fun DetailsPlanetScreenV2(
    href : String,
    viewModel: DetailsPlanetViewModel = viewModel(factory = DetailsPlanetViewModel.factory(href))
) {
    Text(text = href)
}