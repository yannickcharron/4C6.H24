package ca.qc.cstj.composables.ui.composables.meditations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.qc.cstj.composables.ui.theme.ButtonBlue
import ca.qc.cstj.composables.ui.theme.TextWhite

@Composable
fun CurrentMeditationCard(meditationContent: MeditationContent) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = CardDefaults.cardColors(meditationContent.color)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = meditationContent.title, style = MaterialTheme.typography.headlineSmall)
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(ButtonBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun FeatureMeditationCard(meditationContent: MeditationContent) {
    Card(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(meditationContent.color)
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = meditationContent.title,
                style = MaterialTheme.typography.headlineSmall,
                lineHeight = 22.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = meditationContent.icon, contentDescription = meditationContent.title)
                Text(
                    text = "Start",
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(ButtonBlue)
                        .padding(vertical = 6.dp, horizontal = 15.dp)
                )
            }
        }
    }
}