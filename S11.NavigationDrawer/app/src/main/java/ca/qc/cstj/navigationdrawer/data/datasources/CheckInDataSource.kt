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

    fun create(checkIn: CheckIn) {
        //TODO:
    }

    fun retrieveAll() : List<CheckIn> {
        //TODO:
        return listOf()
    }

}