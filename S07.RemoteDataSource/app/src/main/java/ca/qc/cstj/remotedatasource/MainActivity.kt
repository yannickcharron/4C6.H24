package ca.qc.cstj.remotedatasource

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ca.qc.cstj.remotedatasource.ui.screens.NavGraphs

import ca.qc.cstj.remotedatasource.ui.screens.planets.PlanetsScreen
import ca.qc.cstj.remotedatasource.ui.theme.RemoteDataSourceTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemoteDataSourceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}

