package ca.qc.cstj.bottomnavigation.ui.navigation.main

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph


@RootNavGraph
@NavGraph
annotation class SecondLevelNavGraph(
    val start : Boolean = false
)
