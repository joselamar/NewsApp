package lamarao.jose.newsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import lamarao.jose.newsapp.entities.NewsResponse

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsResponse WHERE newsType = :newsType ")
    fun getNewsResponseByType(newsType: String = "General"): Flow<NewsResponse?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(vararg newsResponse: NewsResponse)
}
