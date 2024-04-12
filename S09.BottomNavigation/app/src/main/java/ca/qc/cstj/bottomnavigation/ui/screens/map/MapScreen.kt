package ca.qc.cstj.bottomnavigation.ui.screens.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ca.qc.cstj.bottomnavigation.ui.navigation.main.SecondLevelNavGraph
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination

@SecondLevelNavGraph
@Destination
@Composable
fun MapScreen(coord: LatLng) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(coord, 10f)
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                isBuildingEnabled = false,
                isTrafficEnabled = true
            )
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = mapProperties,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = coord),
            title = "${coord.latitude} ${coord.longitude}",
            snippet = "Yannick"
        )

//        AdvancedMarker(
//            state = MarkerState(position = coord),
//            title = "${coord.latitude} ${coord.longitude}",
//            snippet = "Yannick",
//            pinConfig = PinConfig.builder().setBackgroundColor(Color.GREEN).build()
//        )
    }
}