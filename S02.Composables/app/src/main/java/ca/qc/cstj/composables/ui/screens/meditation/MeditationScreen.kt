package ca.qc.cstj.composables.ui.screens.meditation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.composables.R
import ca.qc.cstj.composables.models.Meditation
import ca.qc.cstj.composables.ui.composables.meditations.CurrentMeditationCard
import ca.qc.cstj.composables.ui.composables.meditations.FeatureMeditationCard
import ca.qc.cstj.composables.ui.composables.meditations.MeditationContent
import ca.qc.cstj.composables.ui.theme.ButtonBlue
import ca.qc.cstj.composables.ui.theme.ComposablesTheme
import ca.qc.cstj.composables.ui.theme.DarkButtonBlue
import ca.qc.cstj.composables.ui.theme.DeepBlue
import ca.qc.cstj.composables.ui.theme.TextWhite

@Composable
fun MeditationScreen(viewModel: MeditationScreenViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        HeaderSection(name = uiState.value.name)
        TagSection(
            tags = uiState.value.tags,
            selectedTag = uiState.value.selectedTag,
            onTagClick = { selectedTag -> viewModel.updateTag(selectedTag) }
        )
        CurrentMeditationCard(MeditationContent(uiState.value.currentMeditation))
        FeaturesSection(
            meditations = uiState.value.featuresMeditations,
            onStartClick = { index ->
                Log.d("Yannick:MeditationScreen", "1")
                viewModel.startMeditation(index)
            })
    }
}

@Composable
fun FeaturesSection(meditations: List<Meditation>, onStartClick: (Int) -> Unit) {

    val meditationContents = meditations.map { m ->
        MeditationContent(m)
    }
    
    Text(
        text = stringResource(R.string.features),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
    )
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp)
    ) {
        itemsIndexed(meditationContents) { index, meditation ->
            FeatureMeditationCard(meditationContent = meditation, onStartClick =
            {
                Log.d("Yannick:FeaturesSection", "2")
                onStartClick(index)
            })
        }
    }
}

@Composable
fun TagSection(
    tags: List<String>,
    selectedTag: String,
    onTagClick: (String) -> Unit
) {

    Log.d("TagSection", selectedTag)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 15.dp)
    ) {
        items(tags) {
            Box(modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable {
                    onTagClick(it)
                    //viewModel.updateTag(selectedTag)
                }
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (selectedTag == it) {
                        ButtonBlue
                    } else {
                        DarkButtonBlue
                    }
                )
                .padding(10.dp), contentAlignment = Alignment.Center) {
                Text(text = it, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun HeaderSection(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.good_morning, name), style = MaterialTheme.typography.headlineSmall
            )
            Text(text = stringResource(id = R.string.welcome_message), style = MaterialTheme.typography.bodyMedium)
        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "Search",
            tint = TextWhite
        )
    }
}


@Preview(
    showBackground = true, locale = "fr-rCA"
)
@Composable
fun MeditationScreenPreview() {
    ComposablesTheme {
        MeditationScreen()
    }
}