package lamarao.jose.newsapp.ui.newsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import lamarao.jose.newsapp.R
import lamarao.jose.newsapp.databinding.NewsDetailsFragmentBinding

class NewsDetails : Fragment() {

  private val newsDetailsViewModel: NewsDetailsViewModel by viewModels()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return DataBindingUtil.inflate<NewsDetailsFragmentBinding>(
            inflater, R.layout.news_details_fragment, container, false)
        .apply {
          viewModel = newsDetailsViewModel
          lifecycleOwner = viewLifecycleOwner
        }
        .root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    newsDetailsViewModel.navigateToMain.observe(viewLifecycleOwner) {
      if (it == true) { // Observed state is true.
        this.findNavController().popBackStack()
        newsDetailsViewModel.doneNavigating()
      }
    }
  }
}
