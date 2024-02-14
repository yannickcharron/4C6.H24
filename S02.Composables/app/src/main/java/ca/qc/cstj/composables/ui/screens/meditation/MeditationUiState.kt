package ca.qc.cstj.composables.ui.screens.meditation

import ca.qc.cstj.composables.models.Meditation

//Représente les données nécessaire à l'écran pour conserver l'état et les changements dans ce dernier
data class MeditationUiState(
    val name: String = "",
    val tags: List<String> = listOf(),
    val selectedTag: String = "",
    val currentMeditation: Meditation = Meditation(),
    val featuresMeditations: List<Meditation> = listOf()
)
