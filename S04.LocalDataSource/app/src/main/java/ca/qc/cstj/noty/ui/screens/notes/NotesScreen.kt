package ca.qc.cstj.noty.ui.screens.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ca.qc.cstj.noty.ui.theme.NotyTheme

@Composable
fun NotesScreen(navController : NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add")
            }) {
                Icon(Icons.Filled.Add, contentDescription = Icons.Filled.Add.name)
            }
        }
    ) { innerPaddings ->
        Column(modifier = Modifier.padding(innerPaddings)) {
            Text(text = "Notes")
        }
    }
}


@Preview
@Composable
fun NotesScreenPreview() {
    NotyTheme {
        NotesScreen(rememberNavController())
    }
}
