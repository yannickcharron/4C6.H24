package ca.qc.cstj.bottomnavigation.ui.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun FavoritesScreen() {

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Text("FavoritesScreen")
    }
}