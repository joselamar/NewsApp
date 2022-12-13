package lamarao.jose.newsapp.system.network.api

import lamarao.jose.newsapp.entities.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("category") category: String? = "General",
        @Query("country") country: String? = "us",
        @Query("pageSize") pageSize: Int? = 50
    ): Response<NewsResponse>
}
