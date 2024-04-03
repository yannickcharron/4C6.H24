package ca.qc.cstj.bottomnavigation.ui.navigation.core

import ca.qc.cstj.bottomnavigation.ui.navigation.Screen
import ca.qc.cstj.bottomnavigation.ui.screens.destinations.Destination
import ca.qc.cstj.bottomnavigation.ui.screens.destinations.FavoritesScreenDestination
import ca.qc.cstj.bottomnavigation.ui.screens.destinations.MediaPlayerScreenDestination
import ca.qc.cstj.bottomnavigation.ui.screens.destinations.ProfileScreenDestination
import ca.qc.cstj.bottomnavigation.ui.screens.destinations.WeatherScreenDestination


val Destination.screen
    get() : Screen {
        return when(this) {
            FavoritesScreenDestination -> Screen.FavoritesScreen
            MediaPlayerScreenDestination -> Screen.MediaPlayerScreen
            ProfileScreenDestination -> Screen.ProfileScreen
            WeatherScreenDestination -> Screen.WeatherScreen
        }
    }