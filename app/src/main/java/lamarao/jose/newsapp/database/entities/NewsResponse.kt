package lamarao.jose.newsapp.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Entity
data class NewsResponse(
    @PrimaryKey var index: Int?,
    var articles: List<Article>,
    val status: String,
    val totalResults: Int
) : Parcelable
