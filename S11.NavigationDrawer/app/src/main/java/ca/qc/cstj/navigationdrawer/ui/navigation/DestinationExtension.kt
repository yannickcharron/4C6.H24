package ca.qc.cstj.navigationdrawer.ui.navigation

import ca.qc.cstj.navigationdrawer.ui.screens.destinations.CheckInScreenDestination
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.Destination
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.DetailsPlanetScreenDestination
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.OrientationScreenDestination
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.OthersScreenDestination
import ca.qc.cstj.navigationdrawer.ui.screens.destinations.PlanetsScreenDestination

val Destination.screen
    get() : Screen {
        return when(this) {
            DetailsPlanetScreenDestination -> Screen.DetailsPlanet
            PlanetsScreenDestination -> Screen.Planets
            OthersScreenDestination -> Screen.Fun
            CheckInScreenDestination -> Screen.CodeQR
            OrientationScreenDestination -> Screen.Orientation

            //EXAMEN: Navigation lien entre destination et screen (vous devez avoir builder avant) CTRL + F9
        }
    }