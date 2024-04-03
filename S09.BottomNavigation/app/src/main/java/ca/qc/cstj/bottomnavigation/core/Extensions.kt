package ca.qc.cstj.bottomnavigation.core

operator fun <K, V> Map<out K, V>.plus(map: Map<out K, V>): Map<K, V> =
    LinkedHashMap(this).apply { putAll(map) }

