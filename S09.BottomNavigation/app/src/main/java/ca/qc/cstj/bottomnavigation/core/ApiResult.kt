package ca.qc.cstj.bottomnavigation.core

sealed interface ApiResult<out T> {
    data object Loading : ApiResult<Nothing>
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error(val exception: Exception, val message: String = "") : ApiResult<Nothing>

}