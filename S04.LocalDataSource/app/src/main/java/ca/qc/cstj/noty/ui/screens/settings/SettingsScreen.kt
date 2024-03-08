package ca.qc.cstj.noty.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ca.qc.cstj.noty.R
import ca.qc.cstj.noty.core.Constants
import ca.qc.cstj.noty.core.toColor
import ca.qc.cstj.noty.models.NotySettings
import ca.qc.cstj.noty.ui.theme.NotyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsScreenViewModel = viewModel(),
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, null
                        )
                    }
                },
            )

        },
    ) { innerPaddings ->
        SettingScreenContainer(
            modifier = Modifier.padding(innerPaddings),
            settings = uiState.settings,
            onNameChange = { viewModel.saveName() },
            onColorChange = { viewModel.saveColor(it) }
        )
    }

}

@Composable
fun SettingScreenContainer(
    modifier: Modifier = Modifier,
    settings: NotySettings,
    onNameChange: (String) -> Unit,
    onColorChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxHeight()
    ) {
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = settings.name,
            onValueChange = {
                onNameChange(it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {   }),
            label = { Text("Name") },
            leadingIcon = {
                Icon(
                    Icons.Filled.AccountCircle, "error", tint = MaterialTheme.colorScheme.outline
                )
            })

        LazyVerticalGrid(
            modifier = Modifier, contentPadding = PaddingValues(8.dp), columns = GridCells.Fixed(4)
        ) {
            items(Constants.NOTES_COLORS) {
                Box(modifier = Modifier
                    .padding(
                        end = 8.dp, top = 8.dp
                    )
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(it.toColor)
                    .clickable {
                        onColorChange(it)
                    }
                ) {
                    if (settings.noteColor == it) {
                        Icon(
                            imageVector = Icons.Sharp.CheckCircle,
                            contentDescription = Icons.Sharp.CheckCircle.toString(),
                            tint = Color.Black,
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }

                }
            }
        }
        ElevatedButton(onClick = { }) {
            Text(text = "Reset")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
   NotyTheme {
       SettingScreenContainer(
           settings = NotySettings("Yannick", Constants.NOTES_COLORS.random()),
           onColorChange = {},
           onNameChange = {}
       )
   }
}