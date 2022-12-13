package lamarao.jose.newsapp.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import lamarao.jose.newsapp.common.ResultWrapper
import lamarao.jose.newsapp.database.dao.NewsDao
import lamarao.jose.newsapp.entities.NewsResponse
import lamarao.jose.newsapp.system.network.api.NewsServiceApi
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException
import java.time.Duration
import java.time.Instant
import java.util.Locale
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val newsDao: NewsDao,
    private val newsService: NewsServiceApi,
) {

    private val listOfAvailableCountries = listOf(
        "AE", "AR", "AT", "AU", "BE", "BG", "BR", "CA", "CH",
        "CN", "CO", "CU", "CZ", "DE", "EG", "FR", "GB", "GR",
        "HK", "HU", "ID", "IE", "IL", "IN", "IT", "JP", "KR",
        "LT", "LV", "MA", "MX", "MY", "NG", "NL", "NO", "NZ",
        "PH", "PL", "PT", "RO", "RS", "RU", "SA", "SE", "SG",
        "SI", "SK", "TH", "TR", "TW", "UA", "US", "VE", "ZA"
    )

    suspend fun getNews(newsType: String): Flow<ResultWrapper<NewsResponse?>> = flow {
        val articles = newsDao.getNewsResponseByType(newsType).firstOrNull()
        if (articles == null || isCacheStale(articles.timestamp)) {
            try {
                val result = newsService.getNews(newsType, getCountryCode())
                if (result.isSuccessful) {
                    emit(ResultWrapper.Success(result.body()))
                    result.body()?.let {
                        newsDao.insertArticles(it.copy(newsType = newsType))
                    }
                } else {
                    emit(ResultWrapper.Error(result.code()))
                }
            } catch (e: UnknownHostException) {
                Timber.w("Error fetching news : ${e.stackTrace}")
                emit(ResultWrapper.Error(DEFAULT_ERROR_CODE))
            } catch (e: ConnectException) {
                Timber.w("Error fetching news : ${e.stackTrace}")
                emit(ResultWrapper.Error(DEFAULT_ERROR_CODE))
            }
        } else {
            emit(ResultWrapper.Success(articles))
        }
    }

    // Due to api request quota limitations it's necessary to constraint this caching
    private fun isCacheStale(timestamp: String?): Boolean {
        return Duration.between(Instant.parse(timestamp), Instant.now())
            .toHours() > MAX_CACHE_TIME_IN_HOURS
    }

    // We fetch the country of the headlines from the locale
    private fun getCountryCode(): String {
        val countryIsoCode = Locale.getDefault().country
        return if (countryIsoCode in listOfAvailableCountries) {
            countryIsoCode
        } else {
            "us"
        }
    }

    companion object {
        private const val MAX_CACHE_TIME_IN_HOURS = 8
        private const val DEFAULT_ERROR_CODE = 10001
    }
}
