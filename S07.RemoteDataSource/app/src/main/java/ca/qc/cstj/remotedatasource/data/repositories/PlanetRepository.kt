package ca.qc.cstj.remotedatasource.data.repositories

import ca.qc.cstj.remotedatasource.data.datasources.PlanetDataSource
import ca.qc.cstj.remotedatasource.models.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlanetRepository {

    private val planetDataSource = PlanetDataSource()
    fun retrieveAll() : Flow<List<Planet>> {

        return flow {
            while(true) {
                try {
                    //emit équivalent à return mais dans flow
                    emit(planetDataSource.retrieveAll())
                } catch (ex: Exception) {
                    emit(listOf())
                }
                delay(60000)
            }
        }.flowOn(Dispatchers.IO)

    }

}