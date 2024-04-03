package ca.qc.cstj.bottomnavigation.ui.screens.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.NetworkService
import ca.qc.cstj.bottomnavigation.models.CurrentWeather
import ca.qc.cstj.bottomnavigation.ui.components.ErrorMessage
import ca.qc.cstj.bottomnavigation.ui.components.LoadingAnimation
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.skydoves.landscapist.glide.GlideImage

@RootNavGraph(start = true)
@Destination
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel()
) {
    val searchTextState by viewModel.searchTextState
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchField(searchText = searchTextState,
            onChangeSearchText = { viewModel.updateSearchTextState(it) },
            onSearchClick = { viewModel.search() }
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is WeatherUiState.Error -> {
                ErrorMessage(uiState.error)
            }

            WeatherUiState.Loading -> {
                LoadingAnimation()
            }

            is WeatherUiState.Success -> {
                CurrentWeatherSection(currentWeather = uiState.currentWeather)
            }

            WeatherUiState.Idle -> { /* Nothing to Show here*/ }
        }

    }
}

@Composable
fun CurrentWeatherSection(currentWeather: CurrentWeather) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.6f),
        colors =CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "currentWeather.city",
                style = MaterialTheme.typography.headlineMedium
            )
            GlideImage(modifier = Modifier.size(64.dp),
                imageModel = { NetworkService.FLAGS_API_URL.format("currentWeather.country") })
            Text(
                text = "currentWeather.locationDateTime.format()",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "currentWeather.systemDefaultDate.toLocalDateTimeFormat()",
                style = MaterialTheme.typography.bodyMedium
            )
            WeatherImage(currentWeather = currentWeather)
            Text(
                text = stringResource(
                    R.string.temperature_value_in_celsius, "currentWeather.temperature.toString()"
                ),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                text = "currentWeather.weather",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp), text = stringResource(
                    R.string.feels_like_temperature_in_celsius, "currentWeather.feelsLike.toString()"
                ), style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = stringResource(id = R.string.geographic_position, "currentWeather.latitude", "currentWeather.longitude"),
                style = MaterialTheme.typography.bodySmall
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.GpsFixed, contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun SearchField(
    searchText: String,
    onChangeSearchText: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = { onChangeSearchText(it) },
        label = {
            Text(text = "Search for a city")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            }
        })
}

