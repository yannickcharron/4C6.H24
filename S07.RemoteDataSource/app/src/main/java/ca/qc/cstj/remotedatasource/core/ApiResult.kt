package ca.qc.cstj.remotedatasource.core

import ca.qc.cstj.remotedatasource.models.Planet

sealed class ApiResult<out T> {
    data object Loading : ApiResult<Nothing>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception, val message: String = "") : ApiResult<Nothing>()

}