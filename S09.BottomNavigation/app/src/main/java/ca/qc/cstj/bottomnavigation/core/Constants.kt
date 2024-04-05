package ca.qc.cstj.bottomnavigation.core

object Constants {
    const val DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
    const val NIGHT_HOUR = 18
}

object NetworkService {
    private const val BASE_URL = "https://api.openweathermap.org"
    private const val API_KEY = ""
    private const val UNITS = "metric"
    const val CURRENT_WEATHER_END_POINT = "$BASE_URL/data/2.5/weather"
    const val DEFAULTS_OPTIONS = "&units=${UNITS}&appid=${API_KEY}"
    const val FLAGS_API_URL = "https://flagsapi.com/%s/flat/64.png"
}

object RefreshDelay {
    const val METEO_REFRESH = 1000L * 60
}

object WeatherConditions {
    const val CLEAR_SKY = "clear sky"
    const val FEW_CLOUDS = "few clouds"
    const val SCATTERED_CLOUDS = "scattered clouds"
    const val BROKEN_CLOUDS = "broken clouds"
    const val SHOWER_RAIN = "shower rain"
    const val RAIN = "rain"
    const val THUNDERSTORM = "thunderstorm"
    const val SNOW = "snow"
    const val MIST = "mist"
}

object MainWeatherConditions {
    const val CLOUDS = "Clouds"
    const val SNOW = "Snow"
    const val RAIN = "Rain"
    const val THUNDERSTORM = "Thunderstorm"
    const val CLEAR = "Clear"
}