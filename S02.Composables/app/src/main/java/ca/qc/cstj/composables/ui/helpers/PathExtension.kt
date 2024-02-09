package ca.qc.cstj.composables.ui.helpers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs


//Méthode d'extension
fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x, from.y, abs(from.x + to.x) / 2f, abs(from.y + to.y) / 2f
    )
}

fun colorPaths(width: Float, height: Float): Pair<Path, Path> {

    val mediumColorPoints = listOf(
        Offset(0f, height * 0.3f),
        Offset(width * 0.1f, height * 0.35f),
        Offset(width * 0.4f, height * 0.05f),
        Offset(width * 0.75f, height * 0.7f),
        Offset(width * 1.4f, -height)
    )

    val lightColorsPoints = listOf(
        Offset(0f, height * 0.35f),
        Offset(width * 0.1f, height * 0.4f),
        Offset(width * 0.3f, height * 0.35f),
        Offset(width * 0.65f, height),
        Offset(width * 1.4f, -height / 3f)
    )

    val mediumColoredPath = Path().apply {
        moveTo(mediumColorPoints.first().x, mediumColorPoints.first().y)
        mediumColorPoints.take(mediumColorPoints.size - 1).forEachIndexed { index, offset ->
            standardQuadFromTo(offset, mediumColorPoints[index + 1])
        }
        lineTo(width + 100f, height + 100f)
        lineTo(-100f, height + 100f)
        close()
    }


    val lightColoredPath = Path().apply {
        moveTo(lightColorsPoints.first().x, lightColorsPoints.first().y)
        lightColorsPoints.take(lightColorsPoints.size - 1).forEachIndexed { index, offset ->
            standardQuadFromTo(offset, lightColorsPoints[index + 1])
        }
        lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
        lineTo(-100f, height.toFloat() + 100f)
        close()
    }

    return Pair(mediumColoredPath, lightColoredPath)
}