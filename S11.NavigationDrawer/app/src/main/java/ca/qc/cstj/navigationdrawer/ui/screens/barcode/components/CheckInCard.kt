package ca.qc.cstj.navigationdrawer.ui.screens.barcode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.qc.cstj.navigationdrawer.models.CheckIn

@Composable
fun CheckInCard(checkIn: CheckIn) {

    ElevatedCard(modifier = Modifier.padding(bottom = 4.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = checkIn.door.toString(), style = MaterialTheme.typography.headlineMedium)
                Text(text = checkIn.scanId, style = MaterialTheme.typography.headlineMedium)
            }
            Text(text = checkIn.scannedDate)
        }
    }

}