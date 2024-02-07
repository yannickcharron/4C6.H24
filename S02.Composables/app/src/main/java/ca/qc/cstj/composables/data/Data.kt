package ca.qc.cstj.composables.data

import ca.qc.cstj.composables.models.Meditation


//object => public static class en C#
object Data {

    val meditationColors = listOf("pink", "blue", "green")
    val meditationIcons = listOf("music", "night", "meditation", "headphone")

    val meditationTags = listOf(
        "Sweet sleep",
        "Sleep Tight",
        "Insomnia",
        "Good Dreams",
        "Mood Booster",
        "Depression"
    )


    val meditations = listOf(
        Meditation("Tips for sleeping"),
        Meditation("Night island"),
        Meditation("Calming sounds"),
        Meditation("Space Sounds"),
        Meditation("Sleep meditation"),
        Meditation("Tips for sleeping"),
        Meditation("Night island"),
        Meditation("Calming sounds"),
        Meditation("Space Sounds"),
        Meditation("Sleep meditation"),
        Meditation("Tips for sleeping"),
        Meditation("Night island"),
        Meditation("Calming sounds"),
        Meditation("Space Sounds"),
    )

}