package ca.qc.cstj.composables.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ca.qc.cstj.composables.R

val PoppinsFont = FontFamily(
    Font(R.font.poppins_medium, FontWeight.Medium)
)


val defaultTypography = Typography()
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = TextWhite
    ),
    bodyMedium = defaultTypography.bodyMedium.copy(
        color = TextWhite,
        fontFamily = PoppinsFont
    ),
    headlineMedium = defaultTypography.headlineMedium.copy(
        color = TextWhite,
        fontFamily = PoppinsFont
    ),
    headlineSmall = defaultTypography.headlineSmall.copy(
        color = TextWhite,
        fontFamily = PoppinsFont,
    ),
    bodySmall = defaultTypography.bodySmall.copy(
        color = TextWhite,
        fontFamily = PoppinsFont
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val meditationHeadlineSmall = Typography.headlineSmall.copy(
    color = TextWhite,
    fontFamily = PoppinsFont,
)