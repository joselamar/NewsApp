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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.concurrent.Executor
import lamarao.jose.newsapp.R
import lamarao.jose.newsapp.databinding.InitialScreenFragmentBinding

class InitialScreen : Fragment() {

  private lateinit var executor: Executor
  private lateinit var biometricPrompt: BiometricPrompt
  private lateinit var promptInfo: BiometricPrompt.PromptInfo

  private lateinit var _binding: InitialScreenFragmentBinding
  private val binding
    get() = _binding

  private val viewModel: InitialScreenViewModel by lazy {
    ViewModelProvider(this).get(InitialScreenViewModel::class.java)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    _binding = DataBindingUtil.inflate(inflater, R.layout.initial_screen_fragment, container, false)

    // set lifecycleowner and viewModel
    binding.lifecycleOwner = viewLifecycleOwner
    binding.viewModel = viewModel

    checkBiometrics()

    viewModel.showPrompt.observe(
        viewLifecycleOwner,
        {
          if (it == true) {
            biometricPrompt.authenticate(promptInfo)
            // Reset state to make sure we only show the prompt once, even if the device
            // has a configuration change.
            viewModel.promptShowed()
          }
        })

    viewModel.navigateToMain.observe(
        viewLifecycleOwner,
        {
          if (it == true) {
            this.findNavController()
                .navigate(InitialScreenDirections.actionInitialScreenToMainFragment())
            // Reset state to make sure we only navigate once, even if the device
            // has a configuration change.
            viewModel.doneNavigating()
          }
        })

    return binding.root
  }

  // verify if user has biometrics enabled and act accordingly. if user can authenticate set
  // biometrics and show prompt else navigate to main activity fragment
  private fun checkBiometrics() {
    val biometricManager = BiometricManager.from(requireContext())
    when (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {
      BiometricManager.BIOMETRIC_SUCCESS -> {
        setBiometrics()
        viewModel.showPrompt()
      }
      else -> viewModel.doneAuthentication()
    }
  }

  // set BiometricPrompt logic and promptInfo
  private fun setBiometrics() {
    executor = ContextCompat.getMainExecutor(context)
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
                viewModel.doneAuthentication()
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
