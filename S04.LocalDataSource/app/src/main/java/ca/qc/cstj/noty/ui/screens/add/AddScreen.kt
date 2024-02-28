package ca.qc.cstj.noty.ui.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SubdirectoryArrowLeft
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ca.qc.cstj.noty.R
import ca.qc.cstj.noty.core.Constants
import ca.qc.cstj.noty.core.toColor
import ca.qc.cstj.noty.models.Note
import ca.qc.cstj.noty.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavHostController,
    viewModel : AddScreenViewModel = viewModel()
) {

    //Va changer à chacune des modifications d'état déclenchant une recomposition
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.save()
            }) {
                Icon(Icons.Filled.Save, contentDescription = Icons.Filled.Add.name)
            }
        }
    ) { innerPaddings ->
        AddScreenContent(
            note = uiState.value.note,
            onUpdateTitle = { newTitle -> viewModel.updateTitle(newTitle) },
            onUpdateContent = { viewModel.updateContent(it) },
            onUpdateColor = { viewModel.updateColor(it) },
            modifier = Modifier.padding(innerPaddings),
        )
    }
}

@Composable
fun AddScreenContent(
    note : Note,
    onUpdateTitle : (String) -> Unit,
    onUpdateContent: (String) -> Unit,
    onUpdateColor :  (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "New Note",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "Title", style = MaterialTheme.typography.bodyLarge)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = note.title,
            onValueChange = { newValue ->
                onUpdateTitle(newValue)
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            singleLine = false,
            value = note.content,
            onValueChange = {
                onUpdateContent(it)
            }
        )

        Text(text = "Color", style = MaterialTheme.typography.bodyLarge)

        LazyVerticalGrid(columns = GridCells.Fixed(4), contentPadding = PaddingValues(8.dp)) {
            //foreach
            items(Constants.NOTES_COLORS) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp, top = 8.dp)
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(it.toColor)
                        .clickable {
                            onUpdateColor(it)
                        }
                ) {
                    if(note.color == it) {
                        Icon(
                            imageVector = Icons.Sharp.CheckCircle,
                            contentDescription = Icons.Sharp.CheckCircle.toString(),
                            tint = Color.Black,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }

//        LazyVerticalGrid(columns = GridCells.Fixed(4) ,
//            content = { items(Constants.NOTES_COLORS) {
//
//            } })
    }
}