package lamarao.jose.newsapp.database

import android.os.Parcelable
import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "author") val author: String?,
    @Json(name = "content") val content: String?,
    @Json(name = "description") val description: String,
    @Json(name = "publishedAt") val publishedAt: String,
    @Json(name = "source") @Embedded val source: Source,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String,
    @Json(name = "urlToImage") val urlToImage: String?
) : Parcelable
