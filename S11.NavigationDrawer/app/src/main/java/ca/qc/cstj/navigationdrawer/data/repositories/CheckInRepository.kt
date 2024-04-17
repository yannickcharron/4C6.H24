package ca.qc.cstj.navigationdrawer.data.repositories

import ca.qc.cstj.navigationdrawer.core.ApiResult
import ca.qc.cstj.navigationdrawer.data.datasources.CheckInDataSource
import ca.qc.cstj.navigationdrawer.models.CheckIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CheckInRepository {

    private val checkInDataSource = CheckInDataSource()

    fun retrieveAll() {
        //TODO:
    }

    fun create(checkIn: CheckIn) {
        //TODO:
    }

}