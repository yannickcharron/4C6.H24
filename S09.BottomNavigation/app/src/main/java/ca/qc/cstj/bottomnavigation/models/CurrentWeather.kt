package ca.qc.cstj.bottomnavigation.models

import ca.qc.cstj.bottomnavigation.core.DateHelper
import ca.qc.cstj.bottomnavigation.data.datasources.remote.weatherapi.dto.CurrentWeatherDTO
import kotlinx.datetime.LocalDateTime

class CurrentWeather(dto: CurrentWeatherDTO) {
    val city :String = dto.name
    val country : String = dto.sys.country
    val locationDateTime : LocalDateTime = DateHelper.toCurrentWeatherLocationDateTime(dto.dt.toLong(), dto.timezone)
    val systemDefaultDate: LocalDateTime = DateHelper.toSystemDefaultDateTime(dto.dt.toLong(), dto.timezone)
    val description : String = dto.weather[0].description
    val icon : String = dto.weather[0].icon
    val weather :String = dto.weather[0].main
    val temperature : Double = dto.main.temp
    val feelsLike : Double = dto.main.feelsLike
    val latitude : Double = dto.coord.lat
    val longitude : Double = dto.coord.lon
}