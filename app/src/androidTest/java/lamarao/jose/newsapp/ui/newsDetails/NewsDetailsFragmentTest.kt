package lamarao.jose.newsapp.ui.newsDetails

import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import lamarao.jose.newsapp.R
import lamarao.jose.newsapp.database.Article
import lamarao.jose.newsapp.database.Source
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NewsDetailsFragmentTest {

    @Test
    fun test_isNewsDetailsVisible(){
        val article = Article(source = Source("bbc-news","BBC News"), author="BBC News", title = "Lina Khan: The 32-year-old taking on Big Tech", description = "The new Federal Trade Commission chair has Big Tech in her sights.", url = "http://www.bbc.co.uk/news/technology-57501579", urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/4406/production/_118941471_hi066892410.jpg",publishedAt = "2021-06-16T19:22:26.1558931Z", content = "By James ClaytonNorth America technology reporter\r\nOn Tuesday, 32-year-old Lina Khan was sworn in as chair of the US Federal Trade Commission (FTC).\r\nThe role is a hugely powerful one, which protects… [+4719 chars]")
        val bundle = Bundle()
        bundle.putParcelable("article",article)

        val scenario = launchFragmentInContainer<NewsDetails>(fragmentArgs = bundle)
        onView(withId(R.id.article_title_nd)).check(matches(withText(article.title)))
        onView(withId(R.id.article_description_nd)).check(matches(withText(article.description)))
    }
}