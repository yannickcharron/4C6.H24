package ca.qc.cstj.navigationdrawer.ui.navigation

import androidx.annotation.StringRes
import ca.qc.cstj.navigationdrawer.R

sealed class Screen(
    @StringRes val titleId: Int,
    val topBarOptions: TopBarOptions = TopBarOptions.None,
    val bottomBarOptions: BottomBarOptions = BottomBarOptions.None,
) {
    data object Planets : Screen(
        titleId = R.string.planets,
        topBarOptions = TopBarOptions(true),
        bottomBarOptions = BottomBarOptions(false)
    )

    data object CodeQR : Screen(
        titleId = R.string.code_qr,
        topBarOptions = TopBarOptions(true),
        bottomBarOptions = BottomBarOptions(false)
    )

    data object Fun : Screen(
        titleId = R.string.fun_screen,
        topBarOptions = TopBarOptions(true),
        bottomBarOptions = BottomBarOptions(false)
    )

    data object Orientation : Screen(
        titleId = R.string.orientation,
        topBarOptions = TopBarOptions(true),
        bottomBarOptions = BottomBarOptions(false)
    )

    data object DetailsPlanet: Screen(
        titleId = R.string.planets,
        topBarOptions = TopBarOptions(false),
        bottomBarOptions = BottomBarOptions(false)
    )

    //EXAMEN: Possibilité de devoir ajouter un ou des écrans ici ...

}

open class TopBarOptions(
    val isTopBarVisible: Boolean = true,
    val isBackButtonVisible: Boolean = false
) {
    data object None : TopBarOptions(false, false)
}

open class BottomBarOptions(
    val isBottomBarVisible: Boolean = false,
    val selectedBottomBarItem: BottomNavigationItem? = null
) {
    data object None : BottomBarOptions()
}