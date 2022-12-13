package lamarao.jose.newsapp.repositories.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import lamarao.jose.newsapp.entities.Colors
import lamarao.jose.newsapp.entities.Themes
import timber.log.Timber
import javax.inject.Inject

class DatastoreRepository @Inject constructor(
    private val datastore: DataStore<Preferences>
) {

    fun getPreferredColor(): Flow<String> = datastore.data.catch {
        Timber.w("Error reading datastore")
        emptyPreferences()
    }.map { settings ->
        settings[PREFERRED_COLOR] ?: Colors.BLUE.name
    }

    suspend fun setPreferredColor(color: String) {
        datastore.edit { settings ->
            settings[PREFERRED_COLOR] = color
        }
    }

    fun getPreferredTheme(): Flow<String> = datastore.data.catch {
        Timber.w("Error reading datastore")
        emptyPreferences()
    }.map { settings ->
        settings[PREFERRED_THEME] ?: Themes.DEFAULT_THEME.toString()
    }

    suspend fun setPreferredTheme(theme: String) {
        Timber.i(theme)
        datastore.edit { settings ->
            settings[PREFERRED_THEME] = theme
        }
    }

    companion object {
        private val PREFERRED_COLOR = stringPreferencesKey("preferredColor")
        private val PREFERRED_THEME = stringPreferencesKey("preferredTheme")
    }
}
