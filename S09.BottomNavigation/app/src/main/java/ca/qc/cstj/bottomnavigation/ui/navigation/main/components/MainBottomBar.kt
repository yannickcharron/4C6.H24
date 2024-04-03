package ca.qc.cstj.bottomnavigation.ui.navigation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ca.qc.cstj.bottomnavigation.ui.navigation.Screen
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec


@Composable
fun MainBottomBar(
    currentScreen : Screen,
    onNavigate: (DirectionDestinationSpec) -> Unit,
) {

    //TODO: Ajouter les items
    val mainBottomBarItems = listOf<MainBottomBarItem>()

    var internalCurrentScreen by remember { mutableStateOf(currentScreen)}
    internalCurrentScreen = currentScreen.bottomBarOptions.selectedBottomBarItem?.screen ?: currentScreen


}