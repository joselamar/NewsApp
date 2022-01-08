package lamarao.jose.newsapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import lamarao.jose.newsapp.database.entities.NewsResponse

@Dao
interface NewsDao {

  @Query("select * from NewsResponse") fun getNewsResponse(): LiveData<NewsResponse>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertArticles(vararg newsResponse: NewsResponse)
}
