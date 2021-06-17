package lamarao.jose.newsapp.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.delay
import lamarao.getOrAwaitValue
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@RunWith(AndroidJUnit4::class)
class NewsDatabaseTest : TestCase() {

    private lateinit var db: NewsDatabase
    private lateinit var dao: NewsDAO


    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()

        dao = db.newsDAO
    }

    @After
    fun closeDB() {
        db.close()
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun writeAndReadNewsResponse() {
        val article = Article(
            source = Source("bbc-news", "BBC News"),
            author = "BBC News",
            title = "Lina Khan: The 32-year-old taking on Big Tech",
            description = "The new Federal Trade Commission chair has Big Tech in her sights.",
            url = "http://www.bbc.co.uk/news/technology-57501579",
            urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/4406/production/_118941471_hi066892410.jpg",
            publishedAt = "2021-06-16T19:22:26.1558931Z",
            content = "By James ClaytonNorth America technology reporter\r\nOn Tuesday, 32-year-old Lina Khan was sworn in as chair of the US Federal Trade Commission (FTC).\r\nThe role is a hugely powerful one, which protects… [+4719 chars]"
        )
        val newsResponse = NewsResponse(1, listOf(article), status = "ok", totalResults = 1)
        dao.insertArticles(newsResponse)
        val newsResponseDB = dao.getNewsResponse().getOrAwaitValue()
        assertEquals(newsResponseDB, newsResponse)
    }
}
