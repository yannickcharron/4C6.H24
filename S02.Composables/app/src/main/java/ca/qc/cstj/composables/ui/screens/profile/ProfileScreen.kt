package ca.qc.cstj.composables.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.qc.cstj.composables.R
import ca.qc.cstj.composables.ui.theme.ComposablesTheme
import ca.qc.cstj.composables.ui.theme.DeepBlue


@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PilotBanner()
        StatsSection()
    }
}


@Composable
fun PilotBanner() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.pilot04),
            contentDescription = "Pilot"
        )
        Text(text = "Yannick", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Niveau 1", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun StatsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatValue()
        StatValue()
        StatValue()
        StatValue()
    }
}

@Composable
fun StatValue() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .size(48.dp),
            painter = painterResource(id = R.drawable.cube),
            contentDescription = "Stat"
        )
        Text(text = "10", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Stat", style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true, locale = "fr-rCA")
@Composable
fun MeditationScreenPreview() {
    ComposablesTheme {
        ProfileScreen()
    }
}