package ca.qc.cstj.bottomnavigation.data.repositories

import ca.qc.cstj.bottomnavigation.core.ApiResult
import ca.qc.cstj.bottomnavigation.data.datasources.remote.weatherapi.CurrentWeatherDataSource
import ca.qc.cstj.bottomnavigation.models.CurrentWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrentWeatherRepository {
    private val currentWeatherDataSource: CurrentWeatherDataSource = CurrentWeatherDataSource()

    fun retrieveWithCityName(cityName: String) :  Flow<ApiResult<CurrentWeather>> {

        return flow {

        }
    }

}