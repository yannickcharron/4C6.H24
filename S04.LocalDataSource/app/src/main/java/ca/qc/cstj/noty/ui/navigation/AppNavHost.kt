package ca.qc.cstj.noty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ca.qc.cstj.noty.ui.screens.notes.NotesScreen


@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "notes"
    ) {
        composable("notes") {
            NotesScreen(navController = navController)
        }
    }
}