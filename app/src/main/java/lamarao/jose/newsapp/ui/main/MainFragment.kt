package lamarao.jose.newsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import lamarao.jose.newsapp.R
import lamarao.jose.newsapp.databinding.MainFragmentBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

  private val mainViewModel: MainViewModel by viewModels()
  private lateinit var binding: MainFragmentBinding
  private lateinit var newsAdapter: NewsAdapter

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    binding =
        DataBindingUtil.inflate<MainFragmentBinding>(
                inflater, R.layout.main_fragment, container, false)
            .apply {
              viewModel = mainViewModel
              lifecycleOwner = viewLifecycleOwner
            }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    newsAdapter =
        NewsAdapter(NewsClickListener { article -> mainViewModel.onArticleClicked(article) })

    // Add an Observer to the variable that retrieves the articles and set it to the recycler view
    mainViewModel.newsResponse.distinctUntilChanged().observe(viewLifecycleOwner) { newsResponse ->
      newsResponse?.apply { newsAdapter.submitList(this.articles) }
    }

    // initiate recycler view with gridlayout and the rv adapter.
    binding.rvNews.apply {
      layoutManager =
          GridLayoutManager(
              context,
              resources.getInteger(
                  R.integer
                      .grid_column_count)) // On orientation change the grid layout displays two
      // columns
      adapter = newsAdapter
      addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
      addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL))
    }

    // Add an Observer on the state variable for Navigating when and item is clicked.
    mainViewModel.navigateToNewsDetail.observe(viewLifecycleOwner) { article ->
      article?.let {
        this.findNavController()
            .navigate(MainFragmentDirections.actionMainFragmentToNewsDetails(article))
        mainViewModel.onArticleDetailNavigated()
      }
    }
  }
}
