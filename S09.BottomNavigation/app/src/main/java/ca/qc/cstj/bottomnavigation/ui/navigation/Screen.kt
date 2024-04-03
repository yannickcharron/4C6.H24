package ca.qc.cstj.bottomnavigation.ui.navigation

import androidx.annotation.StringRes
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.ui.navigation.core.NavigationItem
import ca.qc.cstj.bottomnavigation.ui.navigation.main.components.MainBottomBarItem

sealed class Screen(
    @StringRes val titleId: Int,
    val topBarOptions: TopBarOptions = TopBarOptions.None,
    val bottomBarOptions: BottomBarOptions = BottomBarOptions.None,
) {

    data object WeatherScreen : Screen(
        titleId = R.string.weather,
        topBarOptions = TopBarOptions.Default,
        bottomBarOptions = BottomBarOptions(isBottomBarVisible = true)
    )

    data object FavoritesScreen : Screen(
        titleId = R.string.favorites,
        topBarOptions = TopBarOptions.Default,
        bottomBarOptions = BottomBarOptions(isBottomBarVisible = true)
    )

    data object ProfileScreen : Screen(
        titleId = R.string.profile,
        topBarOptions = TopBarOptions(isTopBarVisible = false),
        bottomBarOptions = BottomBarOptions(isBottomBarVisible = true)
    )

    data object MediaPlayerScreen : Screen(
        titleId = R.string.player,
        topBarOptions = TopBarOptions(isBackButtonVisible = true),
        bottomBarOptions = BottomBarOptions(
            isBottomBarVisible = true,
            selectedBottomBarItem = MainBottomBarItem.ProfileItem
        )
    )

    val isBottomBarVisible : Boolean
        get() = bottomBarOptions.isBottomBarVisible

    val isTopBarVisible: Boolean
        get() = topBarOptions.isTopBarVisible

}


open class TopBarOptions(
    val isTopBarVisible: Boolean = true,
    val isBackButtonVisible: Boolean = false
) {
    data object None : TopBarOptions(false, false)
    data object Default : TopBarOptions()
}

open class BottomBarOptions(
    val isBottomBarVisible: Boolean = false,
    val selectedBottomBarItem: NavigationItem? = null
) {
    data object None : BottomBarOptions()
}


