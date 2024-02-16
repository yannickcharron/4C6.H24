package ca.qc.cstj.composables.ui.screens.profile

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.composables.R
import ca.qc.cstj.composables.models.Pilot
import ca.qc.cstj.composables.ui.theme.ButtonBlue
import ca.qc.cstj.composables.ui.theme.ComposablesTheme
import ca.qc.cstj.composables.ui.theme.DeepBlue
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen(viewModel: ProfileScreenViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PilotBanner(uiState.pilot)
        StatsSection(uiState.pilot)
        AnimationSection()
        LaunchConfiguration(onLiftOffClick = {
            viewModel.liftOff()
        })
    }
}

@Composable
fun AnimationSection() {

    val rocketAngle = remember {
        Animatable(0f)
    }

    LaunchedEffect(true) {
        launch {
            rocketAngle.animateTo(
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
                )
            )
        }
    }



    ConstraintLayout(modifier = Modifier.padding(top = 16.dp)) {

        val (planet, rocket) = createRefs()

        Image(
            modifier = Modifier
                .size(250.dp)
                .constrainAs(planet) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            painter = painterResource(id = R.drawable.planet),
            contentDescription = "Planet"
        )

        Image(
            modifier = Modifier
                .size(60.dp)
                .constrainAs(rocket) {
                    rotationZ = rocketAngle.value % 360 + 90f
                    circular(planet, rocketAngle.value, 130.dp)
                },
            painter = painterResource(id = R.drawable.rocket),
            contentDescription = "Rocket"
        )
    }
}

@Composable
fun LaunchConfiguration(onLiftOffClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(.4f),
        colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
        onClick = { onLiftOffClick() }) {
        Text(text = stringResource(R.string.lift_off))
    }
}


@Composable
fun PilotBanner(pilot: Pilot) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.pilot04),
            contentDescription = "Pilot"
        )
        Text(text = pilot.name, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 4.dp))
        Text(text = stringResource(R.string.level, pilot.level), style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun StatsSection(pilot: Pilot) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatValue(R.drawable.life, pilot.life, stringResource(R.string.life))
        StatValue(R.drawable.shield, pilot.shield, stringResource(R.string.shield))
        StatValue(R.drawable.battery1, pilot.energy, stringResource(R.string.energy))
        StatValue(R.drawable.cube, pilot.treasure, stringResource(R.string.treasure))
    }
}

@Composable
fun StatValue(@DrawableRes imageId: Int, value: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .size(48.dp),
            painter = painterResource(id = imageId),
            contentDescription = label
        )
        Text(text = value.toString(), style = MaterialTheme.typography.bodyLarge)
        Text(text = label, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true, locale = "fr-rCA")
@Composable
fun MeditationScreenPreview() {
    ComposablesTheme {
        ProfileScreen()
    }
}