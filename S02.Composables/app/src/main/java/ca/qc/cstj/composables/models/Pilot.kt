package ca.qc.cstj.composables.models

data class Pilot(val name: String) {

    private var _experience: Int = 0
    private var _life: Int = 10
    private var _shield: Int = 5
    private var _energy: Int = 10
    private var _treasure: Int = 0

    val level: Int get() = _experience / 10 + 1
    val life: Int get() = _life
    val shield: Int get() = _shield
    val energy: Int get() = _energy
    val treasure: Int get() = _treasure

}