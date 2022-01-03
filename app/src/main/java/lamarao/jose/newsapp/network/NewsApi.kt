package lamarao.jose.newsapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import lamarao.jose.newsapp.BuildConfig.sources
import lamarao.jose.newsapp.database.NewsResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// link
// https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=4ffe516959e444e9b30e82262d37adb4
private const val API_KEY = "4ffe516959e444e9b30e82262d37adb4"
private const val BASE_URL = "https://newsapi.org/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface NewsApiService {
  @GET("v2/top-headlines")
  fun getNewsAsync(
      @Query("sources") src: String = sources,
      @Query("apiKey") apiKey: String = API_KEY
  ): Deferred<NewsResponse>
}

object NewsAPI {
  val retrofitService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}
