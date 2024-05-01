package ca.qc.cstj.navigationdrawer.ui.screens.planets.detailplanet

import android.content.res.Configuration
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.navigationdrawer.R
import ca.qc.cstj.navigationdrawer.models.Planet
import ca.qc.cstj.navigationdrawer.ui.components.ErrorMessage
import ca.qc.cstj.navigationdrawer.ui.components.LoadingAnimation
import ca.qc.cstj.navigationdrawer.ui.screens.planets.detailplanet.components.PortalCard
import ca.qc.cstj.navigationdrawer.ui.screens.planets.PlanetCard
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun DetailsPlanetScreen(planet: Planet) {
    val orientation = LocalConfiguration.current.orientation
    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        PortraitLayout(planet)
    } else if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeLayout(planet)
    }
}

@Composable
fun PortraitLayout(planet: Planet) {
    val lazyListState = rememberLazyListState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        PlanetCard(planet = planet)
        Text(text = stringResource(R.string.portals), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn(modifier = Modifier.size(800.dp), state = lazyListState) {
            items(planet.portals) {
                PortalCard(it)
            }
        }

    }
}

@Composable
fun LandscapeLayout(planet: Planet) {
    Row(modifier = Modifier.fillMaxSize()) {
        PlanetCard(modifier = Modifier.fillMaxWidth(0.5f), planet = planet)
        Column {
            Text(text = stringResource(R.string.portals), style = MaterialTheme.typography.headlineMedium)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(planet.portals) {
                    PortalCard(it)
                }
            }
        }
    }
}