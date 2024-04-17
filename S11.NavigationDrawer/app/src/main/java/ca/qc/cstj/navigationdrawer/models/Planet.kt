package ca.qc.cstj.navigationdrawer.models

import kotlinx.serialization.Serializable
import org.json.JSONObject



@Serializable
data class Planet(
    val href: String,
    val name: String,
    val icon: String,
    val temperature: Double,
    val satellites: List<String>,
    val position: Position,
    val portals: List<Portal>
)

@Serializable
data class Position(val x: Double, val y: Double, val z: Double)

@Serializable
data class Portal(val position: String, val affinity: String)


