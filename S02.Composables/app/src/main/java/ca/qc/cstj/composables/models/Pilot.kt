package ca.qc.cstj.composables.models

import kotlin.random.Random

data class Pilot(val name: String) {

    private var _experience: Int = 0
    private var _life: Int = 10
    private var _shield: Int = 5
    private var _energy: Int = 10
    private var _treasure: Int = 0
    private var _isFlying: Boolean = false

    val level: Int get() = _experience / 10 + 1
    val life: Int get() = _life
    val shield: Int get() = _shield
    val energy: Int get() = _energy
    val treasure: Int get() = _treasure
    val isFlying: Boolean get() = _isFlying


    fun fly() {
        if (canFly()) {
            _isFlying = true
            _energy--
        }
    }

    fun completedFlight(revolutions: Int) {
        _isFlying = false
        _life -= Random.nextInt(0, 2) //[0 ; 2[ => entre 0 et 1
        _shield -= (0..2).random() //[0 ; 2] => entre 0 et 2
        _treasure += Random.nextInt(0, revolutions)
        _experience += revolutions * Random.nextInt(1, level + 2)
    }

    private fun canFly(): Boolean {
        //Pouvoir voler => Pas entrain de voler et energy > 0 et life > 0
        return !_isFlying && _energy > 0 && _life > 0

    }


}