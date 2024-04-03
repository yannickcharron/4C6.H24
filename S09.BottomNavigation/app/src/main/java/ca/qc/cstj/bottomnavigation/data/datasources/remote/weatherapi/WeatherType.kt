package ca.qc.cstj.bottomnavigation.data.datasources.remote.weatherapi

import androidx.annotation.DrawableRes
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.Constants
import ca.qc.cstj.bottomnavigation.core.MainWeatherConditions
import ca.qc.cstj.bottomnavigation.core.WeatherConditions
import kotlinx.datetime.LocalDateTime

sealed class WeatherType(
    val weatherDescription: String, @DrawableRes val id: Int
) {
    data object ClearSkyDay : WeatherType(
        weatherDescription = WeatherConditions.CLEAR_SKY, id = R.drawable.clear_sky_day
    )

    data object ClearSkyNight : WeatherType(
        weatherDescription = WeatherConditions.CLEAR_SKY, id = R.drawable.clear_sky_night
    )

    data object FewCloudsDay : WeatherType(
        weatherDescription = WeatherConditions.FEW_CLOUDS, id = R.drawable.few_clouds_day
    )

    data object FewCloudsNight : WeatherType(
        weatherDescription = WeatherConditions.FEW_CLOUDS, id = R.drawable.clear_sky_night
    )

    data object ScatteredClouds : WeatherType(
        weatherDescription = WeatherConditions.SCATTERED_CLOUDS, id = R.drawable.scattered_clouds
    )

    data object BrokenCloudsDay : WeatherType(
        weatherDescription = WeatherConditions.BROKEN_CLOUDS, id = R.drawable.broken_clouds_day
    )

    data object BrokenCloudsNight : WeatherType(
        weatherDescription = WeatherConditions.BROKEN_CLOUDS, id = R.drawable.broken_clouds_night
    )

    data object ShowerRainDay : WeatherType(
        weatherDescription = WeatherConditions.SHOWER_RAIN, id = R.drawable.rain_day
    )

    data object ShowerRainNight : WeatherType(
        weatherDescription = WeatherConditions.SHOWER_RAIN, id = R.drawable.rain_night
    )

    data object RainDay : WeatherType(
        weatherDescription = WeatherConditions.RAIN, id = R.drawable.rain_day
    )

    data object RainNight : WeatherType(
        weatherDescription = WeatherConditions.RAIN, id = R.drawable.rain_day
    )

    data object Thunderstorm : WeatherType(
        weatherDescription = WeatherConditions.THUNDERSTORM, id = R.drawable.thunderstorrm
    )

    data object Snow : WeatherType(
        weatherDescription = WeatherConditions.SNOW, id = R.drawable.snow
    )

    data object MistDay : WeatherType(
        weatherDescription = WeatherConditions.MIST, id = R.drawable.mist_day
    )

    data object MistNight : WeatherType(
        weatherDescription = WeatherConditions.MIST, id = R.drawable.mist_night
    )

    companion object {
        fun factory(
            mainDescription: String,
            weatherDescription: String,
            datetime : LocalDateTime
        ): WeatherType {
            when (mainDescription) {
                MainWeatherConditions.CLOUDS -> {
                    return if (weatherDescription == ScatteredClouds.weatherDescription) {
                        ScatteredClouds
                    } else if (weatherDescription == FewCloudsDay.weatherDescription) {
                        if (datetime.hour > Constants.NIGHT_HOUR) {
                            FewCloudsNight
                        } else {
                            FewCloudsDay
                        }
                    } else {
                        if (datetime.hour > Constants.NIGHT_HOUR) {
                            BrokenCloudsNight
                        } else {
                            BrokenCloudsDay
                        }
                    }
                }

                MainWeatherConditions.RAIN -> {
                    return if (weatherDescription == ShowerRainDay.weatherDescription) {
                        if (datetime.hour > Constants.NIGHT_HOUR) {
                            ShowerRainNight
                        } else {
                            ShowerRainDay
                        }
                    } else {
                        if (datetime.hour > Constants.NIGHT_HOUR) {
                            RainNight
                        } else {
                            RainDay
                        }
                    }
                }

                MainWeatherConditions.THUNDERSTORM -> {
                    return Thunderstorm
                }

                MainWeatherConditions.SNOW -> {
                    return Snow
                }

                MainWeatherConditions.CLEAR -> {
                    return if (datetime.hour > Constants.NIGHT_HOUR) {
                        ClearSkyNight
                    } else {
                        ClearSkyDay
                    }
                }

                else -> {
                    return if (datetime.hour > Constants.NIGHT_HOUR) {
                        MistNight
                    } else {
                        MistDay
                    }
                }
            }
        }
    }
}