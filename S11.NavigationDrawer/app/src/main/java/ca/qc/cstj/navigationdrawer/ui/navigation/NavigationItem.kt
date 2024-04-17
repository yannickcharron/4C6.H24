package ca.qc.cstj.navigationdrawer.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.CheckInScreenDestination
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.OrientationScreenDestination
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.OthersScreenDestination
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.PlanetsScreenDestination
import ca.qc.cstj.navigationdrawer.ui.screens.orientation.OrientationScreen
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

sealed class DrawerNavigationItem(
    destination: DirectionDestinationSpec,
    screen: Screen,
    @StringRes labelId : Int = 0,
    @DrawableRes iconId: Int = 0,
    imageVector: ImageVector? = null
) : NavigationItem( destination, screen, labelId, iconId, imageVector) {

    companion object {
        val Items = listOf(
            Planets,
            Barcode,
            Others,
            Orientation
        )
    }

    data object Planets: DrawerNavigationItem(
        destination = PlanetsScreenDestination,
        screen = Screen.Planets,
        imageVector = Icons.Default.AccountBox
    )

    //EXAMEN: Ajout des 3 autres items du drawer être en mesure de créer les éléments manquants
    data object Barcode : DrawerNavigationItem(
        destination = CheckInScreenDestination,
        screen = Screen.CodeQR,
        imageVector = Icons.Default.QrCode2,
    )

    data object Others : DrawerNavigationItem(
        destination = OthersScreenDestination ,
        screen = Screen.Fun,
        imageVector = Icons.Default.Cake,
    )

    data object Orientation : DrawerNavigationItem(
        destination = OrientationScreenDestination,
        screen = Screen.Orientation,
        imageVector = Icons.Default.Landscape,
    )

}

sealed class BottomNavigationItem(
    destination: DirectionDestinationSpec,
    screen: Screen,
    @StringRes labelId : Int = 0,
    @DrawableRes iconId: Int = 0,
    imageVector: ImageVector? = null
) : NavigationItem( destination, screen, labelId, iconId, imageVector)