package ca.qc.cstj.bottomnavigation.ui.navigation

import androidx.annotation.StringRes
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.ui.navigation.main.components.MainBottomBarItem

sealed class Screen(
    @StringRes val titleId: Int,
    val topBarOptions: TopBarOptions = TopBarOptions.None,
    val bottomBarOptions: BottomBarOptions = BottomBarOptions.None,
) {
    //TODO: Définir les Screens ici
}


open class TopBarOptions(
    val isTopBarVisible: Boolean = true,
    val isBackButtonVisible: Boolean = false
) {
    data object None : TopBarOptions(false, false)
}

open class BottomBarOptions(
    val isBottomBarVisible: Boolean = false,
    val selectedBottomBarItem: MainBottomBarItem? = null
) {
    data object None : BottomBarOptions()
}


