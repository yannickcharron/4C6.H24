package ca.qc.cstj.bottomnavigation.ui.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.ui.screens.destinations.MediaPlayerScreenDestination
import ca.qc.cstj.bottomnavigation.ui.navigation.main.MainViewModel
import ca.qc.cstj.bottomnavigation.ui.navigation.main.components.MainBottomBarItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

@Destination
@Composable
fun FavoritesScreen() {

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Text("FavoritesScreen")
    }
}