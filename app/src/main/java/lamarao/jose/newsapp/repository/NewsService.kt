package lamarao.jose.newsapp.repository

import lamarao.jose.newsapp.BuildConfig.sources
import lamarao.jose.newsapp.database.entities.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

  @GET("v2/top-headlines")
  suspend fun getNews(
      @Query("sources") src: String = sources,
  ): Response<NewsResponse>
}
