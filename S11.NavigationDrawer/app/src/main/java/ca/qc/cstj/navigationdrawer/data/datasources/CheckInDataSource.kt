package ca.qc.cstj.navigationdrawer.data.datasources

import ca.qc.cstj.navigationdrawer.core.Constants
import ca.qc.cstj.navigationdrawer.models.CheckIn
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CheckInDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun create(checkIn: CheckIn) : CheckIn {
        val body = json.encodeToString(checkIn)
        val (_, _, result) = Constants.BaseURL.CHECKIN_URL
            .httpPost()
            .jsonBody(body)
            .responseJson()

        return when(result) {
            is Result.Success -> {
                json.decodeFromString(result.value.content)
            }
            is Result.Failure -> throw result.error.exception
        }
    }

    fun retrieveAll() :List<CheckIn> {
        val (_, _, result) = Constants.BaseURL.CHECKIN_URL.httpGet().responseJson()

        return when (result) {
            is Result.Success -> json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.getException().exception
        }

    }

}