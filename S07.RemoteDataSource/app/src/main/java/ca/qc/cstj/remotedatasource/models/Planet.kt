package ca.qc.cstj.remotedatasource.models

import org.json.JSONObject

data class Planet(
    val name: String,
    val temperature: Float,
    val icon: String
) {
    constructor(planetJson: JSONObject) : this(
        name = planetJson.getString("name"),
        temperature = planetJson.getDouble("temperature").toFloat(),
        icon = planetJson.getString("icon")
    )
}