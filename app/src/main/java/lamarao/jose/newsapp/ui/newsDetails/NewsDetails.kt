package lamarao.jose.newsapp.ui.newsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import lamarao.jose.newsapp.R
import lamarao.jose.newsapp.databinding.NewsDetailsFragmentBinding

class NewsDetails : Fragment() {

  private lateinit var newsDetailsViewModelFactory: NewsDetailsViewModelFactory

  private val viewModel: NewsDetailsViewModel by lazy {
    val arguments = NewsDetailsArgs.fromBundle(requireArguments())
    newsDetailsViewModelFactory = NewsDetailsViewModelFactory(arguments.article)
    ViewModelProvider(this, newsDetailsViewModelFactory).get(NewsDetailsViewModel::class.java)
  }

  private lateinit var _binding: NewsDetailsFragmentBinding
  private val binding
    get() = _binding

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    _binding = DataBindingUtil.inflate(inflater, R.layout.news_details_fragment, container, false)

    binding.lifecycleOwner = viewLifecycleOwner
    binding.viewModel = viewModel

    // Add an Observer to the state variable for Navigating
    viewModel.navigateToMain.observe(
        viewLifecycleOwner,
        {
          if (it == true) { // Observed state is true.
            this.findNavController()
                .navigate(NewsDetailsDirections.actionNewsDetailsToMainFragment())
            // Reset state to make sure we only navigate once, even if the device
            // has a configuration change.
            viewModel.doneNavigating()
          }
        })

    return binding.root
  }
}
