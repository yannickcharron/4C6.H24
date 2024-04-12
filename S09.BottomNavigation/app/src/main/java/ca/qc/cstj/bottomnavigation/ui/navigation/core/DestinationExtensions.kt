package ca.qc.cstj.bottomnavigation.ui.navigation.core

import ca.qc.cstj.bottomnavigation.ui.destinations.Destination
import ca.qc.cstj.bottomnavigation.ui.destinations.FavoritesScreenDestination
import ca.qc.cstj.bottomnavigation.ui.destinations.MainScreenDestination
import ca.qc.cstj.bottomnavigation.ui.destinations.MapScreenDestination
import ca.qc.cstj.bottomnavigation.ui.destinations.MediaPlayerScreenDestination
import ca.qc.cstj.bottomnavigation.ui.destinations.ProfileScreenDestination
import ca.qc.cstj.bottomnavigation.ui.destinations.SplashScreenDestination
import ca.qc.cstj.bottomnavigation.ui.destinations.WeatherScreenDestination
import ca.qc.cstj.bottomnavigation.ui.navigation.Screen


val Destination.screen
    get() : Screen {
        return when(this) {
            FavoritesScreenDestination -> Screen.FavoritesScreen
            MediaPlayerScreenDestination -> Screen.MediaPlayerScreen
            ProfileScreenDestination -> Screen.ProfileScreen
            WeatherScreenDestination -> Screen.WeatherScreen
            MapScreenDestination -> Screen.MapScreen
            MainScreenDestination -> Screen.MainScreen
            SplashScreenDestination -> Screen.SplashScreen
        }
    }