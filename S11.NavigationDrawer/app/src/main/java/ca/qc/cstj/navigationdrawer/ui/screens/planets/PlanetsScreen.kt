package ca.qc.cstj.navigationdrawer.ui.screens.planets

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ca.qc.cstj.navigationdrawer.R
import ca.qc.cstj.navigationdrawer.core.Constants
import ca.qc.cstj.navigationdrawer.models.Planet
import ca.qc.cstj.navigationdrawer.ui.components.ErrorMessage
import ca.qc.cstj.navigationdrawer.ui.components.LoadingAnimation
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.DetailsPlanetScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalLayoutApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun PlanetsScreen(navController: NavController, viewModel: PlanetsScreenViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    AnimatedContent(
        targetState = uiState, transitionSpec = {
            fadeIn(tween(durationMillis = 300)) togetherWith fadeOut(tween(durationMillis = 300))
        }, label = "Content Animation"
    ) { it ->
        when (it) {
            is PlanetsUiState.Error -> {
                ErrorMessage(ex = it.exception)
            }

            PlanetsUiState.Loading -> {
                LoadingAnimation()
            }

            is PlanetsUiState.Success -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        FilterChip(selected = it.temperatureUnit == Constants.TemperatureUnit.Celsius,
                            onClick = { viewModel.changeTemperatureUnit(Constants.TemperatureUnit.Celsius) },
                            label = { Text(text = "Celsius") })
                        FilterChip(selected = it.temperatureUnit == Constants.TemperatureUnit.Fahrenheit,
                            onClick = { viewModel.changeTemperatureUnit(Constants.TemperatureUnit.Fahrenheit) },
                            label = { Text(text = "Fahrenheit ") })
                        FilterChip(selected = it.temperatureUnit == Constants.TemperatureUnit.Kelvin,
                            onClick = { viewModel.changeTemperatureUnit(Constants.TemperatureUnit.Kelvin) },
                            label = { Text(text = "Kelvin ") })
                    }
                    LazyColumn {
                        items(it.planets) { planet ->
                            PlanetCard(planet = planet, unit = it.temperatureUnit, onClick = {
                                navController.navigate(
                                    DetailsPlanetScreenDestination(
                                        planet
                                    )
                                )
                            })
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun PlanetCard(
    modifier: Modifier = Modifier,
    planet: Planet,
    unit: Constants.TemperatureUnit = Constants.TemperatureUnit.Celsius,
    onClick: (Planet) -> Unit = {}
) {

    ElevatedCard(modifier = modifier
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clickable {
            onClick(planet)
        }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GlideImage(
                imageModel = { planet.icon },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center),
                modifier = Modifier.fillMaxWidth(0.4f)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = planet.name, style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = when (unit) {
                        Constants.TemperatureUnit.Kelvin -> stringResource(
                            id = R.string.kelvin_format,
                            planet.temperature
                        )

                        Constants.TemperatureUnit.Celsius -> stringResource(
                            id = R.string.celsius_format,
                            planet.temperature
                        )

                        Constants.TemperatureUnit.Fahrenheit -> stringResource(
                            id = R.string.fahrenheit_format,
                            planet.temperature
                        )
                    }, style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }

}


@Composable
fun PlanetCardEtudiant(planet: Planet) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = planet.name, style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = planet.temperature.toString(), style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }

}

@Preview()
@Composable
fun PlanetsScreenPreview() {

}
