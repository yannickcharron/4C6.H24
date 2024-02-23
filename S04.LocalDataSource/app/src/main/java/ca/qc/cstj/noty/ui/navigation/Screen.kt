package ca.qc.cstj.noty.ui.navigation

sealed class Screen(val route :String) {

    data object Notes : Screen("notes")
    data object Add : Screen("add")
    data object Settings : Screen("settings")

}
