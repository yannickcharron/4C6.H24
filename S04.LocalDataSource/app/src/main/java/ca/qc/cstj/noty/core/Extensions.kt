package ca.qc.cstj.noty.core

import androidx.compose.ui.graphics.Color

val String.toColor
    get() = Color(android.graphics.Color.parseColor(this))