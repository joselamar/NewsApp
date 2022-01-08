package lamarao.jose.newsapp.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lamarao.jose.newsapp.database.entities.Article

class DataTypeConverters {

  @TypeConverter
  fun fromArticleListToString(data: List<Article>): String {
    return Json.encodeToString(data)
  }

  @TypeConverter
  fun toArticleListFromString(json: String): List<Article>? {
    return Json.decodeFromString(json)
  }
}
