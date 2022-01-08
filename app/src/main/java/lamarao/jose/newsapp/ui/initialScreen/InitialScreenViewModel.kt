package lamarao.jose.newsapp.ui.initialScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InitialScreenViewModel : ViewModel() {

  private val _navigateToMain = MutableLiveData<Boolean?>()
  val navigateToMain: LiveData<Boolean?> = _navigateToMain

  private val _showPrompt = MutableLiveData<Boolean?>()
  val showPrompt: LiveData<Boolean?> = _showPrompt

  fun doneAuthentication() {
    _navigateToMain.value = true
  }

  fun doneNavigating() {
    _navigateToMain.value = null
  }

  fun showPrompt() {
    _showPrompt.value = true
  }

  fun promptShowed() {
    _showPrompt.value = null
  }
}
