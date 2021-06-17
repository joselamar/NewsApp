package lamarao.jose.newsapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lamarao.jose.newsapp.database.Article
import lamarao.jose.newsapp.database.getDatabase
import lamarao.jose.newsapp.repository.NewsRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDatabase(application)
    private val newsRepository = NewsRepository(database)


    init {
        viewModelScope.launch {
            newsRepository.refreshArticles()
        }
    }

    var newsResponse= newsRepository.newsResponse


    /**
     * Navigation for the NewsDetail fragment.
     */
    private val _navigateToNewsDetail = MutableLiveData<Article?>()
    val navigateToNewsDetail
        get() = _navigateToNewsDetail

    fun onArticleClicked(article: Article) {
        _navigateToNewsDetail.value = article
    }

    fun onArticleDetailNavigated() {
        _navigateToNewsDetail.value = null
    }

}