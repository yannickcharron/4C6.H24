package ca.qc.cstj.bottomnavigation.data.datasources.remote.weatherapi

import ca.qc.cstj.bottomnavigation.core.NetworkService
import ca.qc.cstj.bottomnavigation.data.datasources.remote.weatherapi.dto.CurrentWeatherDTO
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class CurrentWeatherDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun retrieveWithCityName(cityName: String) : CurrentWeatherDTO  {

        val endpointURL = NetworkService.CURRENT_WEATHER_END_POINT +
                "?q=${cityName}${NetworkService.DEFAULTS_OPTIONS}"

        val (req, res, result)  = endpointURL.httpGet().responseJson()

        return when(result) {
            is Result.Success -> json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.error
        }

    }

}