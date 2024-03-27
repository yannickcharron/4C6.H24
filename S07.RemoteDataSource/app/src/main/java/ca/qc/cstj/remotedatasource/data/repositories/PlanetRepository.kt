package ca.qc.cstj.remotedatasource.data.repositories

import ca.qc.cstj.remotedatasource.core.ApiResult
import ca.qc.cstj.remotedatasource.data.datasources.PlanetDataSource
import ca.qc.cstj.remotedatasource.models.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlanetRepository {

    private val planetDataSource = PlanetDataSource()
    fun retrieveAll() : Flow<ApiResult<List<Planet>>> {

        return flow {
            while(true) {
                try {
                    emit(ApiResult.Loading)
                    //emit équivalent à return mais dans flow
                    emit(ApiResult.Success(planetDataSource.retrieveAll().map {
                        it.copy(temperature = it.temperature - 273.15f)
                    }))
                } catch (ex: Exception) {
                    emit(ApiResult.Error(ex))
                }
                delay(60000)
            }
        }.flowOn(Dispatchers.IO)

    }

}