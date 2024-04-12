package ca.qc.cstj.bottomnavigation.ui.navigation.main.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.ui.destinations.FavoritesScreenDestination
import ca.qc.cstj.bottomnavigation.ui.destinations.ProfileScreenDestination
import ca.qc.cstj.bottomnavigation.ui.destinations.WeatherScreenDestination
import ca.qc.cstj.bottomnavigation.ui.navigation.Screen
import ca.qc.cstj.bottomnavigation.ui.navigation.core.NavigationItem

import com.ramcosta.composedestinations.spec.DirectionDestinationSpec



sealed class MainBottomBarItem(
    destination: DirectionDestinationSpec,
    screen: Screen,
    @StringRes labelId: Int = 0,
    @DrawableRes iconId: Int = 0,
    imageVector: ImageVector? = null
) : NavigationItem(destination, screen, labelId, iconId, imageVector) {

    data object WeatherItem : MainBottomBarItem(
        destination = WeatherScreenDestination,
        screen = Screen.WeatherScreen,
        labelId = R.string.weather,
        imageVector = Icons.Default.WbSunny
    )

    data object FavoritesItem : MainBottomBarItem(
        destination = FavoritesScreenDestination,
        screen = Screen.FavoritesScreen,
        imageVector = Icons.Default.Favorite
    )

    data object ProfileItem : MainBottomBarItem(
        destination = ProfileScreenDestination,
        screen = Screen.ProfileScreen,
        imageVector = Icons.Default.Person
    )


}