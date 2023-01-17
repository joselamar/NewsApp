package lamarao.jose.newsapp.usecases

import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkConstructor
import java.net.ConnectException
import java.time.Duration
import java.util.Locale
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import lamarao.jose.newsapp.common.ResultWrapper
import lamarao.jose.newsapp.database.dao.NewsDao
import lamarao.jose.newsapp.fake.news1
import lamarao.jose.newsapp.system.network.api.NewsServiceApi
import lamarao.jose.newsapp.util.CoroutineTest
import lamarao.jose.newsapp.util.TestTree
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response
import timber.log.Timber

internal class NewsUseCaseTest : CoroutineTest {

    private val mockNewsDao = mockk<NewsDao>(relaxed = true)
    private val mockNewsService = mockk<NewsServiceApi>(relaxed = true)
    private val storedLocale = Locale.getDefault()

    override lateinit var testScope: TestScope

    private lateinit var cut: NewsUseCase
    private val testTree = TestTree()

    @BeforeEach
    fun setUp() {
        Timber.plant(testTree)
        mockkConstructor(Duration::class)
        coEvery { mockNewsService.getNews(any(), any()) } returns Response.success(news1)
        every { mockNewsDao.getNewsResponseByType(any()) } returns flowOf(null)
        coEvery { mockNewsDao.insertArticles(any()) } just Runs
        every { anyConstructed<Duration>().toHours() } returns ONE_HOUR
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
        Locale.setDefault(storedLocale)
    }

    private fun instantiateCut() {
        cut = NewsUseCase(mockNewsDao, mockNewsService)
    }

    @Test
    fun `GIVEN no database entry WHEN getting news successfully THEN it should emit and store result`() =
        runTest {
            instantiateCut()
            val expected = ResultWrapper.Success(news1)

            cut.getNews("GENERAL").collect { actual ->
                actual.data shouldBe expected.data
                actual.code shouldBe expected.code
            }

            coVerify {
                mockNewsDao.getNewsResponseByType(any())
                mockNewsService.getNews(any(), any())
                mockNewsDao.insertArticles(any())
            }
        }

    @Test
    fun `GIVEN no database entry WHEN getting news fails THEN it should emit error with HTTP code`() =
        runTest {
            coEvery { mockNewsService.getNews(any(), any()) } returns Response.error(
                404,
                "".toResponseBody()
            )
            instantiateCut()
            val expected = ResultWrapper.Error(null, code = 404)

            cut.getNews("GENERAL").collect { actual ->
                actual.data shouldBe expected.data
                actual.code shouldBe expected.code
            }

            coVerify {
                mockNewsDao.getNewsResponseByType(any())
                mockNewsService.getNews(any(), any())
            }
            coVerify(exactly = 0) {
                mockNewsDao.insertArticles(any())
            }
        }

    @Test
    fun `GIVEN no database entry WHEN getting news throws exception THEN it should emit default error code and log`() =
        runTest {
            coEvery { mockNewsService.getNews(any(), any()) } throws ConnectException()
            instantiateCut()
            val expected = ResultWrapper.Error(null, code = DEFAULT_ERROR_CODE)

            cut.getNews("GENERAL").collect { actual ->
                actual.data shouldBe expected.data
                actual.code shouldBe expected.code
            }

            coVerify {
                mockNewsDao.getNewsResponseByType(any())
                mockNewsService.getNews(any(), any())
            }
            testTree.logs.last() {
                it.message.contains("Error fetching news")
            }
            coVerify(exactly = 0) {
                mockNewsDao.insertArticles(any())
            }
        }

    @Test
    fun `GIVEN database entry WHEN getting news THEN it should emit value`() = runTest {
        every { mockNewsDao.getNewsResponseByType(any()) } returns flowOf(news1)
        instantiateCut()

        val expected = ResultWrapper.Success(news1)

        cut.getNews("GENERAL").collect { actual ->
            actual.data shouldBe expected.data
            actual.code shouldBe expected.code
        }

        coVerify(exactly = 0) {
            mockNewsService.getNews(any(), any())
            mockNewsDao.insertArticles(any())
        }
    }

    @Test
    fun `GIVEN stale database entry WHEN getting news THEN it should fetch and store result`() =
        runTest {
            every { mockNewsDao.getNewsResponseByType(any()) } returns flowOf(news1)
            every { anyConstructed<Duration>().toHours() } returns NINE_HOURS
            instantiateCut()

            val expected = ResultWrapper.Success(news1)

            cut.getNews("GENERAL").collect { actual ->
                actual.data shouldBe expected.data
                actual.code shouldBe expected.code
            }

            coVerify {
                mockNewsDao.getNewsResponseByType(any())
                mockNewsService.getNews(any(), any())
                mockNewsDao.insertArticles(any())
            }
        }

    @Test
    fun `GIVEN locale not present in list WHEN getting news THEN it should use default one`() =
        runTest {
            Locale.setDefault(Locale("vi","VN"))
            instantiateCut()
            val expected = ResultWrapper.Success(news1)

            cut.getNews("GENERAL").collect { actual ->
                actual.data shouldBe expected.data
                actual.code shouldBe expected.code
            }

            coVerify {
                mockNewsDao.getNewsResponseByType(any())
                mockNewsService.getNews(any(), "US")
                mockNewsDao.insertArticles(any())
            }
        }

    @Test
    fun `GIVEN locale present in list WHEN getting news THEN it should use it`() =
        runTest {
            Locale.setDefault(Locale("pt","PT"))
            instantiateCut()
            val expected = ResultWrapper.Success(news1)

            cut.getNews("GENERAL").collect { actual ->
                actual.data shouldBe expected.data
                actual.code shouldBe expected.code
            }

            coVerify {
                mockNewsDao.getNewsResponseByType(any())
                mockNewsService.getNews(any(), "PT")
                mockNewsDao.insertArticles(any())
            }
        }

    companion object {
        private const val DEFAULT_ERROR_CODE = 10001
        private const val ONE_HOUR = 1L
        private const val NINE_HOURS = 9L
    }

}