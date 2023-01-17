package lamarao.jose.newsapp.common

sealed class ResultWrapper<T>(val data: T? = null, val code: Int? = null) {
    class Success<T>(data: T) : ResultWrapper<T>(data)
    class Error<T>(data: T? = null, code: Int?) : ResultWrapper<T>(data, code)
}
