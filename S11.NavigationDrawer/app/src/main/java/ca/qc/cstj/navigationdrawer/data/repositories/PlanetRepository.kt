package ca.qc.cstj.navigationdrawer.data.repositories

import ca.qc.cstj.navigationdrawer.core.ApiResult
import ca.qc.cstj.navigationdrawer.core.Constants
import ca.qc.cstj.navigationdrawer.data.datasources.PlanetDataSource
import ca.qc.cstj.navigationdrawer.models.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlanetRepository {

    companion object {
        const val PLANET_REFRESH_DELAY = 30000L
        const val PLANET_MOCK_LOADING_DELAY = 1500L
    }

    private val planetDataSource = PlanetDataSource()

    fun retrieveAll() : ApiResult<List<Planet>>{
        return try {
            ApiResult.Success(planetDataSource.retrieveAll())
        } catch(ex : Exception) {
            ApiResult.Error(ex)
        }
    }

    fun retrieveOne(href: String) : Flow<ApiResult<Planet>> {
        return flow {
            while (true) {
                try {
                    emit(ApiResult.Loading)
                    emit(ApiResult.Success(planetDataSource.retrieveOne(href)))

                } catch(ex: Exception) {
                    emit(ApiResult.Error(ex))
                }
                delay(60000)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun retrieveAllWithRefresh(unit: Constants.TemperatureUnit) : Flow<ApiResult<List<Planet>>> {
        return flow {
            while(true) {
                try {
                    emit(ApiResult.Loading)

                    //TODO: Ajout du paramètre unit
                    emit(ApiResult.Success(planetDataSource.retrieveAll().map {
                        when(unit) {
                            Constants.TemperatureUnit.Kelvin -> it
                            Constants.TemperatureUnit.Celsius -> it.copy(temperature = it.temperature - 273.15)
                            Constants.TemperatureUnit.Fahrenheit -> it.copy(temperature = 1.8 * (it.temperature - 273.15) + 32)
                        }
                    }))
                } catch(ex: Exception) {
                    emit(ApiResult.Error(ex))
                }
                delay(PLANET_REFRESH_DELAY)
            }

        }.flowOn(Dispatchers.IO)
    }

}