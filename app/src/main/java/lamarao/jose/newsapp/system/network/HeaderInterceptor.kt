package lamarao.jose.newsapp.system.network

import lamarao.jose.newsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()

    val newRequest = request.newBuilder().addHeader("X-API-KEY", BuildConfig.API_KEY).build()

    return chain.proceed(newRequest)
  }
}
