package lamarao.jose.newsapp.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
@Parcelize
@Entity
data class NewsResponse(
    var articles: List<Article>,
    val status: String,
    val totalResults: Int,
    @PrimaryKey
    val newsType: String = Tabs.GENERAL.name,
    val timestamp: String? = Instant.now().toString()
) : Parcelable
