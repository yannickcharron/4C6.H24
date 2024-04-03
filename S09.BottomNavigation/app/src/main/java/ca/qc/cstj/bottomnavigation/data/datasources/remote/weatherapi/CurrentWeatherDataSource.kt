package ca.qc.cstj.bottomnavigation.data.datasources.remote.weatherapi

import kotlinx.serialization.json.Json

class CurrentWeatherDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun retrieveWithCityName(cityName: String)   {

    }

}