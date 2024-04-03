package ca.qc.cstj.bottomnavigation.ui.navigation.core

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ca.qc.cstj.bottomnavigation.ui.navigation.Screen
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

abstract class NavigationItem(
    val destination: DirectionDestinationSpec,
    val screen: Screen,
    @StringRes private val labelId : Int = 0,
    @DrawableRes private val iconId: Int = 0,
    private val imageVector: ImageVector? = null
) {
    val label: String
        @Composable get() = if(labelId != 0) {
            stringResource(id = labelId)
        } else {
            stringResource(id = screen.titleId)
        }

    val icon : ImageVector
        @Composable get() = if(iconId != 0) {
            ImageVector.vectorResource(id = iconId)
        } else {
            imageVector!!
        }
}