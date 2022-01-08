package lamarao.jose.newsapp.ui.initialScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import java.util.concurrent.Executor
import lamarao.jose.newsapp.R
import lamarao.jose.newsapp.databinding.InitialScreenFragmentBinding

class InitialScreen : Fragment() {

  private lateinit var executor: Executor
  private lateinit var biometricPrompt: BiometricPrompt
  private lateinit var promptInfo: BiometricPrompt.PromptInfo

  private val initialScreenViewModel: InitialScreenViewModel by viewModels()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View =
      DataBindingUtil.inflate<InitialScreenFragmentBinding?>(
              inflater, R.layout.initial_screen_fragment, container, false)
          .apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = initialScreenViewModel
          }
          .root

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    checkBiometrics()

    initialScreenViewModel.showPrompt.observe(viewLifecycleOwner) {
      if (it == true) {
        biometricPrompt.authenticate(promptInfo)
        initialScreenViewModel.promptShowed()
      }
    }

    initialScreenViewModel.navigateToMain.observe(viewLifecycleOwner) {
      if (it == true) {
        this.findNavController()
            .navigate(InitialScreenDirections.actionInitialScreenToMainFragment())
        initialScreenViewModel.doneNavigating()
      }
    }
  }

  // verify if user has biometrics enabled and act accordingly. if user can authenticate set
  // biometrics and show prompt else navigate to main activity fragment
  private fun checkBiometrics() {
    val biometricManager = BiometricManager.from(requireContext())
    when (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {
      BiometricManager.BIOMETRIC_SUCCESS -> {
        setBiometrics()
        initialScreenViewModel.showPrompt()
      }
      else -> initialScreenViewModel.doneAuthentication()
    }
  }

  // set BiometricPrompt logic and promptInfo
  private fun setBiometrics() {
    executor = ContextCompat.getMainExecutor(requireContext())
    biometricPrompt =
        BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
              override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(context, "Authentication error: $errString", Toast.LENGTH_SHORT)
                    .show()
              }

              override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                initialScreenViewModel.doneAuthentication()
                Toast.makeText(context, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
              }

              override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
              }
            })

    promptInfo =
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("News App Biometric login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Cancel")
            .build()
  }
}
