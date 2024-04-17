package ca.qc.cstj.navigationdrawer.core

object Constants  {
    const val DOOR: Int = 8
    const val IMAGE_URL = "https://assets.andromia.science/planets/%s.png"

    object BaseURL {
        private const val BASE_API = "https://api.andromia.science"
        const val PLANETS = "${BASE_API}/planets"
        const val CHECKIN_URL = "${BASE_API}/check-ins"

    }

    enum class TemperatureUnit {
        Kelvin,
        Celsius,
        Fahrenheit
    }


}
