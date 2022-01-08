package lamarao.jose.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import lamarao.jose.newsapp.database.converters.DataTypeConverters
import lamarao.jose.newsapp.database.dao.NewsDao
import lamarao.jose.newsapp.database.entities.NewsResponse

@Database(entities = [NewsResponse::class], version = 1)
@TypeConverters(DataTypeConverters::class)
abstract class NewsDatabase : RoomDatabase() {
  abstract val newsDAO: NewsDao
}
