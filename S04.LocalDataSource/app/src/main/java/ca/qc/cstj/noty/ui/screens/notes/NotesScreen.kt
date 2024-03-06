package ca.qc.cstj.noty.ui.screens.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ca.qc.cstj.noty.R
import ca.qc.cstj.noty.core.toColor
import ca.qc.cstj.noty.core.toDateFormat
import ca.qc.cstj.noty.models.Note
import ca.qc.cstj.noty.ui.navigation.Screen
import ca.qc.cstj.noty.ui.theme.NotyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navController: NavHostController,
    viewModel: NotesScreenViewModel = viewModel()
) {

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
                navController.navigate(Screen.Add.route)
            }) {
                Icon(Icons.Filled.Add, contentDescription = Icons.Filled.Add.name)
            }
        }
    ) { innerPaddings ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPaddings)
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            items(uiState.value.notes) { note ->
                NoteCard(note = note, onDelete = {
                    viewModel.delete(it)
                })
            }
        }
    }
}

@Composable
fun NoteCard(note: Note, onDelete: (Note) -> Unit) {

    var isDeleteDialogOpen by remember {
        mutableStateOf(false)
    }

    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = note.color.toColor)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleLarge)
            Text(text = note.content)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = note.creationDate.toDateFormat())
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.clickable {
                        //onDelete(note)
                        isDeleteDialogOpen = true
                    }
                )
            }
        }
    }

    if (isDeleteDialogOpen) {

        AlertDialog(
            containerColor = note.color.toColor,
            onDismissRequest = { isDeleteDialogOpen = false },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete"
                )
            },
            title = {
                Text(stringResource(R.string.msg_delete_dialog))
            },
            text = { Text(stringResource(R.string.action_cannot_be_undone)) },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(note)
                    isDeleteDialogOpen = false
                }
                ) {
                    Text(stringResource(R.string.delete_it).uppercase())
                }
            },
            dismissButton = {
                TextButton(onClick = { isDeleteDialogOpen = false }) {
                    Text(stringResource(R.string.cancel).uppercase())
                }
            }
        )
    }
}


@Preview
@Composable
fun NotesScreenPreview() {
    NotyTheme {
        NotesScreen(rememberNavController())
    }
}
