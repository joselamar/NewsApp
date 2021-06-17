package lamarao.jose.newsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lamarao.jose.newsapp.R
import lamarao.jose.newsapp.databinding.MainFragmentBinding
import java.util.concurrent.Executor


class MainFragment : Fragment() {

    private lateinit var newsViewModelFactory: MainViewModelFactory
    private lateinit var rvNewsAdapter: RvNewsAdapter

    private lateinit var _binding: MainFragmentBinding
    private val binding get() = _binding

    //initiate viewModel
    private val viewModel: MainViewModel by lazy {
        val application = requireNotNull(this.activity).application
        newsViewModelFactory = MainViewModelFactory(application)
        ViewModelProvider(this,newsViewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        rvNewsAdapter = RvNewsAdapter(NewsClickListener { article -> viewModel.onArticleClicked(article) })

        //Add an Observer to the variable that retrieves the articles and set it to the recycler view
        viewModel.newsResponse.observe(viewLifecycleOwner, { newsResponse ->
            newsResponse?.apply {
                rvNewsAdapter.data = newsResponse.articles
            }
        })

        // initiate recycler view with gridlayout and the rv adapter.
        binding.root.findViewById<RecyclerView>(R.id.rvNews).apply {
            layoutManager = GridLayoutManager(context,resources.getInteger(R.integer.grid_column_count)) // On orientation change the grid layout displays two columns
            adapter = rvNewsAdapter
        }

        // Add an Observer on the state variable for Navigating when and item is clicked.
        viewModel.navigateToNewsDetail.observe(viewLifecycleOwner, { article ->
            article?.let {
                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToNewsDetails(article)
                )
                viewModel.onArticleDetailNavigated()
            }
        })

        return binding.root
    }


}