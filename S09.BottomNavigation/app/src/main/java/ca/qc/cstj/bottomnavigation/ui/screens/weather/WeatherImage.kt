package ca.qc.cstj.bottomnavigation.ui.screens.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.qc.cstj.bottomnavigation.data.datasources.remote.weatherapi.WeatherType
import ca.qc.cstj.bottomnavigation.models.CurrentWeather

@Composable
fun WeatherImage(currentWeather: CurrentWeather) {

    val weatherType = WeatherType.factory(
        currentWeather.weather,
        currentWeather.description,
        currentWeather.locationDateTime
    )

    Image(
        modifier = Modifier.size(128.dp),
        painter = painterResource(id = weatherType.id),
        contentDescription = ""
    )

}