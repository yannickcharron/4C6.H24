 package ca.qc.cstj.navigationdrawer.ui.screens.orientation

import android.content.res.Configuration
import android.widget.Space
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

    val orientation = LocalConfiguration.current.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        //Portrait
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            FirstSection(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
                isLandscape = false
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            SecondSection(modifier =  Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                isLandscape = false
            )
        }
    } else if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
        //Paysage
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
        ) {
            FirstSection(isLandscape = true,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            SecondSection(
                isLandscape = true,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }




}

@Composable
fun FirstSection(isLandscape: Boolean, modifier: Modifier = Modifier) {
    //TODO:
    ElevatedCard(modifier = modifier) {
        Text(text = stringResource(R.string.first))
        Text(text = if(isLandscape) stringResource(R.string.landscape) else stringResource(R.string.portrait))
    }
}


@Composable
fun SecondSection(isLandscape: Boolean, modifier: Modifier = Modifier) {
    //TODO:
    ElevatedCard(modifier = modifier) {
        Text(text = stringResource(R.string.second))
        Text(text = if(isLandscape) stringResource(R.string.landscape) else stringResource(R.string.portrait))
    }
}