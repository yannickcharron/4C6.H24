package ca.qc.cstj.bottomnavigation.data.repositories

import ca.qc.cstj.bottomnavigation.core.ApiResult
import ca.qc.cstj.bottomnavigation.core.RefreshDelay
import ca.qc.cstj.bottomnavigation.data.datasources.remote.weatherapi.CurrentWeatherDataSource
import ca.qc.cstj.bottomnavigation.models.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CurrentWeatherRepository {
    private val currentWeatherDataSource: CurrentWeatherDataSource = CurrentWeatherDataSource()

    fun retrieveWithCityName(cityName: String) :  Flow<ApiResult<CurrentWeather>> {

        return flow {

            while(true) {

                try {
                    emit(ApiResult.Loading)
                    val dto = currentWeatherDataSource.retrieveWithCityName(cityName)
                    val currentWeather = CurrentWeather(dto)
                    emit(ApiResult.Success(currentWeather))
                } catch(ex: Exception) {
                    emit(ApiResult.Error(ex))
                }

                delay(RefreshDelay.METEO_REFRESH)
            }

        }.flowOn(Dispatchers.IO)
    }

}