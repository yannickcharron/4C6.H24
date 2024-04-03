package ca.qc.cstj.bottomnavigation.ui.navigation.main

import ca.qc.cstj.bottomnavigation.ui.navigation.main.components.MainBottomBarItem


data class MainUiState(
    private val _titleFormatArgs: List<Any> = listOf(),
    val bottomBarBadges : Map<MainBottomBarItem, String> = mapOf()
) {
    val titleFormatArgs get() = _titleFormatArgs.toTypedArray()
}