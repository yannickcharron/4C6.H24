package ca.qc.cstj.noty.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ca.qc.cstj.noty.core.Constants
import ca.qc.cstj.noty.models.NotySettings
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "noty-datastore")
class SettingsRepository(private val context: Context) {

    object PreferencesKeys {
        //Dans la TP
        //val TP_ELEMENT = floatPreferencesKey("element1")
        val NAME = stringPreferencesKey("name")
        val DEFAULT_NOTE_COLOR = stringPreferencesKey("default_note_color")
    }

    val settings = context.dataStore.data.map {
        val name = it[PreferencesKeys.NAME] ?: ""
        val defaultNoteColor = it[PreferencesKeys.DEFAULT_NOTE_COLOR] ?: Constants.NOTES_COLORS.random()
         NotySettings(name, defaultNoteColor)
    }

    suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit {
            it[key] = value
        }
    }
}