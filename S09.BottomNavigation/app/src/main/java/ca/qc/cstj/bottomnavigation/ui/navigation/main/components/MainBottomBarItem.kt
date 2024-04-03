package ca.qc.cstj.bottomnavigation.ui.navigation.main.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
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

    //TODO: Définir les items
}