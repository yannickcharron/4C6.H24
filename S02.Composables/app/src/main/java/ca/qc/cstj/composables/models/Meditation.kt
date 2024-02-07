package ca.qc.cstj.composables.models

import ca.qc.cstj.composables.data.Data
import kotlin.random.Random

data class Meditation(
    val title : String,
    val tags: List<String> = Data.meditationTags.shuffled().take(Random.nextInt(2, 4)),
    val icon : String = Data.meditationIcons.random(),
    val color : String = Data.meditationColors.random(),
)
