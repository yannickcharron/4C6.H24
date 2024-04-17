package ca.qc.cstj.navigationdrawer.ui.screens.orientation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.qc.cstj.navigationdrawer.R
import com.ramcosta.composedestinations.annotation.Destination


@Composable
@Destination
fun OrientationScreen() {

    //TODO:
    Column(
        modifier = Modifier.fillMaxSize().padding(4.dp)
    ) {
        FirstSection(isLandscape = false)
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        SecondSection(isLandscape = false)
    }
}

@Composable
fun FirstSection(isLandscape: Boolean) {
    //TODO:
    ElevatedCard {
        Text(text = stringResource(R.string.first))
        Text(text = if(isLandscape) stringResource(R.string.landscape) else stringResource(R.string.portrait))
    }
}


@Composable
fun SecondSection(isLandscape: Boolean) {
    //TODO:
    ElevatedCard {
        Text(text = stringResource(R.string.second))
        Text(text = if(isLandscape) stringResource(R.string.landscape) else stringResource(R.string.portrait))
    }
}