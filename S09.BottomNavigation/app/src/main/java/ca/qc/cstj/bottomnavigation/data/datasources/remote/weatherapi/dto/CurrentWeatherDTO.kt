package ca.qc.cstj.bottomnavigation.data.datasources.remote.weatherapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrentWeatherDTO(
    @SerialName("base")
    val base: String,
    @SerialName("clouds")
    val clouds: Clouds,
    @SerialName("cod")
    val cod: Int,
    @SerialName("coord")
    val coord: Coord,
    @SerialName("dt")
    val dt: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("main")
    val main: Main,
    @SerialName("name")
    val name: String,
    @SerialName("rain")
    val rain: Rain = Rain(),
    @SerialName("sys")
    val sys: Sys,
    @SerialName("timezone")
    val timezone: Int,
    @SerialName("weather")
    val weather: List<Weather>,
    @SerialName("wind")
    val wind: Wind
)

@Serializable
data class Clouds(
    @SerialName("all")
    val all: Int
)

@Serializable
data class Coord(
    @SerialName("lat")
    val lat: Double,
    @SerialName("lon")
    val lon: Double
)

@Serializable
data class Main(
    @SerialName("feels_like")
    val feelsLike: Double,
    @SerialName("grnd_level")
    val grndLevel: Int = 0,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("pressure")
    val pressure: Int,
    @SerialName("sea_level")
    val seaLevel: Int = 0,
    @SerialName("temp")
    val temp: Double,
    @SerialName("temp_max")
    val tempMax: Double,
    @SerialName("temp_min")
    val tempMin: Double
)

@Serializable
data class Rain(
    @SerialName("1h")
    val h: Double = 0.0
)

@Serializable
data class Sys(
    @SerialName("country")
    val country: String,
    @SerialName("id")
    val id: Int,
    @SerialName("sunrise")
    val sunrise: Int,
    @SerialName("sunset")
    val sunset: Int,
    @SerialName("type")
    val type: Int
)

@Serializable
data class Weather(
    @SerialName("description")
    val description: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("id")
    val id: Int,
    @SerialName("main")
    val main: String
)

@Serializable
data class Wind(
    @SerialName("deg")
    val deg: Int,
    @SerialName("gust")
    val gust: Double = 0.0,
    @SerialName("speed")
    val speed: Double
)