package ca.qc.cstj.navigationdrawer.ui.screens.other

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.qc.cstj.navigationdrawer.R
import ca.qc.cstj.navigationdrawer.ui.components.YoutubePlayer
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun OthersScreen() {

    val youtubeId = "dQw4w9WgXcQ"

    Column {

        YoutubePlayer(youtubeVideoId = youtubeId, lifecycleOwner = LocalLifecycleOwner.current)

        SoundSection()
        IntentsSection()
    }
}

@Composable
fun SoundSection() {
    //https://pixabay.com/sound-effects/

    val mediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.chonologyoflove)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            IconButton(onClick = {
                mediaPlayer.start()
            }) {
                Icon(
                    imageVector = Icons.Default.PlayCircle,
                    contentDescription = "",
                    Modifier.size(100.dp)
                )
            }

            IconButton(onClick = {
                mediaPlayer.pause()
            }) {
                Icon(
                    imageVector = Icons.Default.PauseCircle,
                    contentDescription = "",
                    Modifier.size(100.dp)
                )
            }
        }
    }
}

@Composable
fun IntentsSection() {
    val context = LocalContext.current
    LazyRow {
        item {
            Button(onClick = {
               val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:450-436-1580"))
                context.startActivity(dialIntent)
            }) {
                Text(text = stringResource(R.string.phone))
            }
        }

        item {
            Button(onClick = {
                val smsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:5149370707"))
                smsIntent.putExtra("sms_body", "Bonjour c'est pour un déménagement")
                context.startActivity(smsIntent)
            }) {
                Text(text = stringResource(R.string.sms))
            }
        }

        item {
            Button(onClick = {
                val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=restaurants"))
                mapIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(mapIntent)
            }) {
                Text(text = stringResource(R.string.maps))
            }
        }

        item {
            Button(onClick = {
                val calculatorIntent = Intent(Intent.ACTION_MAIN)
                calculatorIntent.addCategory(Intent.CATEGORY_APP_CALCULATOR)
                context.startActivity(calculatorIntent)
            }) {
                Text(text = stringResource(R.string.calculator))
            }
        }

        item {
            Button(onClick = {
                val calculatorIntent = Intent(Intent.ACTION_MAIN)
                calculatorIntent.addCategory(Intent.CATEGORY_APP_CALENDAR)
                context.startActivity(calculatorIntent)
            }) {
                Text(text = stringResource(R.string.calendar))
            }
        }
    }
}