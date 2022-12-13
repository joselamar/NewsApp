package lamarao.jose.newsapp.ui.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lamarao.jose.newsapp.entities.Colors
import lamarao.jose.newsapp.entities.Themes
import lamarao.jose.newsapp.repositories.datastore.DatastoreRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    private val preferredTheme = runBlocking {
        datastoreRepository.getPreferredTheme().first().toString()
    }
    private val _uiState = mutableStateOf(SettingsUiState(preferredTheme))
    val uiState: State<SettingsUiState> = _uiState

    fun onChangeColorClicked(color: Colors) {
        viewModelScope.launch {
            datastoreRepository.setPreferredColor(color.toString())
            _uiState.value = uiState.value.copy(
                isColorDialogVisible = false
            )
        }
    }

    fun onThemeClicked(theme: Themes) {
        if (uiState.value.selectedTheme == theme.toString())
            return
        viewModelScope.launch {
            datastoreRepository.setPreferredTheme(theme.toString())
            _uiState.value = uiState.value.copy(
                isThemeDialogVisible = false,
                selectedTheme = theme.toString()
            )
        }
    }

    fun displayColorDialog() {
        _uiState.value = uiState.value.copy(
            isColorDialogVisible = true
        )
    }

    fun displayThemeDialog() {
        _uiState.value = uiState.value.copy(
            isThemeDialogVisible = true
        )
    }

    fun dismissColorDialog() {
        _uiState.value = uiState.value.copy(
            isColorDialogVisible = false
        )
    }

    fun dismissThemeDialog() {
        _uiState.value = uiState.value.copy(
            isThemeDialogVisible = false
        )
    }
}
