package lamarao.jose.newsapp.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class DataTypeConverters {

  val moshi: Moshi = Moshi.Builder().build()

  @TypeConverter
  fun fromArticleListToString(data: List<Article>): String {
    val type: Type = Types.newParameterizedType(MutableList::class.java, Article::class.java)
    val adapter: JsonAdapter<List<Article>> = moshi.adapter(type)
    return adapter.toJson(data)
  }

  @TypeConverter
  fun toArticleListFromString(json: String): List<Article>? {
    val type: Type = Types.newParameterizedType(MutableList::class.java, Article::class.java)
    val adapter: JsonAdapter<List<Article>> = moshi.adapter(type)
    return adapter.fromJson(json)
  }
}
