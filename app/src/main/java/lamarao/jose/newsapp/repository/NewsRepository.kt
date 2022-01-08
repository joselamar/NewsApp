package lamarao.jose.newsapp.repository

import androidx.lifecycle.LiveData
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lamarao.jose.newsapp.database.NewsDatabase
import lamarao.jose.newsapp.database.entities.NewsResponse
import timber.log.Timber

class NewsRepository
@Inject
constructor(private val database: NewsDatabase, private val newsService: NewsService) {

  // get NewsResponse from db
  val newsResponse: LiveData<NewsResponse> = database.newsDAO.getNewsResponse()

  // make request and update if possible the db record
  suspend fun refreshArticles() {
    withContext(Dispatchers.IO) {
      try {
        val newsResponse = newsService.getNews()
        newsResponse.body()?.let {
          it.index = 1
          it.articles.sortedBy { articles -> articles.publishedAt }
          database.newsDAO.insertArticles(it)
        }
      } catch (e: IOException) {
        Timber.e(e)
      }
    }
  }
}
