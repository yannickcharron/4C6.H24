package ca.qc.cstj.bottomnavigation.ui.navigation.main.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ca.qc.cstj.bottomnavigation.ui.navigation.Screen
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec


@Composable
fun MainBottomBar(
    currentScreen : Screen,
    onNavigate: (DirectionDestinationSpec) -> Unit,
) {

    //TODO: Ajouter les items de la barre et l'ordre d'affichage
    val mainBottomBarItems = listOf(
        MainBottomBarItem.WeatherItem,
        MainBottomBarItem.FavoritesItem,
        MainBottomBarItem.ProfileItem
    )

    var internalCurrentScreen by remember { mutableStateOf(currentScreen)}
    internalCurrentScreen = currentScreen.bottomBarOptions.selectedBottomBarItem?.screen ?: currentScreen

    //MaterialTheme.colorScheme.primary
    NavigationBar(containerColor = Color.Transparent) {
        mainBottomBarItems.forEach {
            NavigationBarItem(
                selected = internalCurrentScreen == it.screen,
                onClick = {
                    onNavigate(it.destination)
                },
                icon = {
                    Icon(modifier = Modifier.size(20.dp),
                        imageVector = it.icon ,
                        contentDescription = it.label,
                        tint = if(internalCurrentScreen == it.screen)
                                    MaterialTheme.colorScheme.onPrimaryContainer
                                else
                                    MaterialTheme.colorScheme.onPrimary
                    )
                },
                label = {
                    Text(
                        text = it.label,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            )
        }
    }



}