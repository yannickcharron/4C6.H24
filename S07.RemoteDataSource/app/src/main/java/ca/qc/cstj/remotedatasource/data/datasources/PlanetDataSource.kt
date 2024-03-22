package ca.qc.cstj.remotedatasource.data.datasources

import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.models.Planet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class PlanetDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun retrieveAll() : List<Planet> {

        val (req, res, result) = Constants.PLANETS_URL.httpGet().responseJson()

        when(result) {
            is Result.Success -> {
                return json.decodeFromString(result.value.content)
//                val planetsJson = result.value.array()
//
//                //Transformer planetsJson en List<Planet>
//                val planets = mutableListOf<Planet>()
//
//                for(i in 0 until planetsJson.length()) {
//                    val planetJson = planetsJson.getJSONObject(i)
//                    val planet = Planet(planetJson)
//                    planets.add(planet)
//                }
//
//                return planets



            }
            is Result.Failure -> {
                throw result.error.exception
            }
        }

    }

}