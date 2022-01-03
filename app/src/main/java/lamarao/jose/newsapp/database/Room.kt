package lamarao.jose.newsapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Dao
interface NewsDAO {

  @Query("select * from NewsResponse") fun getNewsResponse(): LiveData<NewsResponse>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertArticles(vararg newsResponse: NewsResponse)
}

@Database(entities = [NewsResponse::class], version = 1)
@TypeConverters(DataTypeConverters::class)
abstract class NewsDatabase : RoomDatabase() {
  abstract val newsDAO: NewsDAO
}

private lateinit var INSTANCE: NewsDatabase

fun getDatabase(context: Context): NewsDatabase {
  synchronized(NewsDatabase::class.java) {
    if (!::INSTANCE.isInitialized) {
      INSTANCE =
          Room.databaseBuilder(
                  context.applicationContext, NewsDatabase::class.java, "news_database.db")
              .build()
    }
  }
  return INSTANCE
}
