package ca.qc.cstj.bottomnavigation.ui.navigation.main.components

import android.content.res.TypedArray
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ca.qc.cstj.bottomnavigation.ui.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    currentScreen: Screen,
    titleFormatArgs: Array<Any>,
    onNavigateUp: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        title = {
            if (titleFormatArgs.isNotEmpty()) {
                Text(
                    text = stringResource(id = currentScreen.titleId, *titleFormatArgs),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            } else {
                Text(
                    text = stringResource(id = currentScreen.titleId),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        },
        navigationIcon = {
            if (currentScreen.topBarOptions.isBackButtonVisible) {
                IconButton(onClick = { onNavigateUp() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}