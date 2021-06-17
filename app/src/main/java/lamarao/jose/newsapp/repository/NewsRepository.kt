package lamarao.jose.newsapp.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lamarao.jose.newsapp.database.NewsDatabase
import lamarao.jose.newsapp.database.NewsResponse
import lamarao.jose.newsapp.network.NewsAPI
import timber.log.Timber

class NewsRepository(private val database: NewsDatabase) {

    //get NewsResponse from db
    val newsResponse: LiveData<NewsResponse> = database.newsDAO.getNewsResponse()

    // make request and update if possible the db record
    suspend fun refreshArticles(){
        withContext(Dispatchers.IO) {
            try {
                val newsResponse = NewsAPI.retrofitService.getNewsAsync().await()
                newsResponse.index = 1
                newsResponse.articles.sortedBy { it.publishedAt } //sort the articles by date
                database.newsDAO.insertArticles(newsResponse)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}