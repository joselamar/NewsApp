package lamarao.jose.newsapp.ui.newsDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lamarao.jose.newsapp.database.Article

class NewsDetailsViewModelFactory(private val article: Article) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(article) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
