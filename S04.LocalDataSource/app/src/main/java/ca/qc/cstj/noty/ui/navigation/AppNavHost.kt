package ca.qc.cstj.noty.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ca.qc.cstj.noty.ui.screens.add.AddScreen
import ca.qc.cstj.noty.ui.screens.notes.NotesScreen
import ca.qc.cstj.noty.ui.screens.settings.SettingsScreen


@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Notes.route
    ) {
        composable(Screen.Notes.route) {
            NotesScreen(navController = navController)
        }
        composable(Screen.Add.route) {
            AddScreen(navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}