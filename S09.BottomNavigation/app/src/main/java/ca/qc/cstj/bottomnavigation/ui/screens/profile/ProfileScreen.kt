package ca.qc.cstj.bottomnavigation.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ca.qc.cstj.bottomnavigation.ui.navigation.main.SecondLevelNavGraph
import com.ramcosta.composedestinations.annotation.Destination


@SecondLevelNavGraph
@Destination
@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("ProfileScreen")
    }
}
