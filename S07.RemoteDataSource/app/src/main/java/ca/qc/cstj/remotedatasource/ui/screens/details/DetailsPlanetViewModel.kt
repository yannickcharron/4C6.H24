package ca.qc.cstj.remotedatasource.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class DetailsPlanetViewModel(
    private val href:String
) : ViewModel() {

    // Partie statique de la classe
    companion object {
        fun factory(href: String) : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DetailsPlanetViewModel(href)
            }
        }
    }

}
