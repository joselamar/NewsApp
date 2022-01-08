package lamarao.jose.newsapp.system.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import lamarao.jose.newsapp.BuildConfig
import lamarao.jose.newsapp.database.NewsDatabase
import lamarao.jose.newsapp.repository.NewsRepository
import lamarao.jose.newsapp.repository.NewsService
import lamarao.jose.newsapp.system.network.HeaderInterceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class) private val json = Json { explicitNulls = false }

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Singleton
  @Provides
  fun providesHttpLoggingInterceptor() =
      HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

  @Singleton @Provides fun providesHeaderInterceptor() = HeaderInterceptor()

  @Singleton
  @Provides
  fun providesOkHttpClient(
      httpLoggingInterceptor: HttpLoggingInterceptor,
      headerInterceptor: HeaderInterceptor
  ): OkHttpClient =
      OkHttpClient.Builder()
          .addInterceptor(headerInterceptor)
          .addInterceptor(httpLoggingInterceptor)
          .build()

  @OptIn(ExperimentalSerializationApi::class)
  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
      Retrofit.Builder()
          .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
          .baseUrl(BuildConfig.BASE_URL)
          .client(okHttpClient)
          .build()

  @Singleton
  @Provides
  fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

  @Singleton
  @Provides
  fun providesRepository(newsService: NewsService, database: NewsDatabase) =
      NewsRepository(database, newsService)
}
