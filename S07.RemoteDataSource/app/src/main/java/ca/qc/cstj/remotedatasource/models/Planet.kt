package ca.qc.cstj.remotedatasource.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONObject

@Serializable
data class Planet(
    val name: String,
    val temperature: Float,
    @SerialName("icon") val image: String
) {
//    constructor(planetJson: JSONObject) : this(
//        name = planetJson.getString("name"),
//        temperature = planetJson.getDouble("temperature").toFloat(),
//        icon = planetJson.getString("icon")
//    )
}