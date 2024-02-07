package ca.qc.cstj.composables.ui.composables.meditations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ca.qc.cstj.composables.models.Meditation
import ca.qc.cstj.composables.ui.theme.Blue3
import ca.qc.cstj.composables.ui.theme.Green3
import ca.qc.cstj.composables.ui.theme.Pink3


data class MeditationContent(
    val title: String,
    private var _icon: ImageVector = Icons.Default.Headphones,
    private var _color: Color = Blue3
) {
    val color: Color get() = _color
    val icon: ImageVector get() = _icon
    constructor(meditation: Meditation) : this(meditation.title) {

        //Transformer l'icône (string -> ImageVector)
        _icon = when(meditation.icon) {
            "music" -> Icons.Default.MusicNote
            "night" -> Icons.Default.NightsStay
            "headphone" -> Icons.Default.Headphones
            "meditation" -> Icons.Default.SelfImprovement
            else -> Icons.Default.Headphones
        }

        //Transformer la couleur (string -> Color)
        _color = when (meditation.color) {
            "blue" -> Blue3
            "pink" -> Pink3
            "green" -> Green3
            else -> Blue3
        }

    }
}