package com.example.newsfeedapp.api

import com.example.newsfeedapp.data.model.Article
import com.example.newsfeedapp.data.sources.remoteApi.ApiService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class ArticlesServiceTest : BaseServiceTest<ApiService>() {

    private lateinit var service: ApiService
    private lateinit var article: Article


    @Before
    fun initService() {
        service = createService(ApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchArticlesListTest()   {
        enqueueResponse("/ArticlesResponse.json")
        runBlocking {
            article = requireNotNull(service.getArticlesNews("the-next-web").articles?.get(0))
        }
        mockWebServer.takeRequest()
        assertThat(article.author,`is`("The Markup"))
        assertThat(article.title,`is`("Companies made millions building unemployment websites that didn't work"))
        assertThat(article.description,`is`("In 2010, California hired the consulting firm Deloitte to overhaul the state website people use to apply for unemployment benefits. Things didn’t go well: Later that year, technical ..."))

        assertThat(article.url,`is`("https://thenextweb.com/syndication/2020/07/19/companies-made-millions-building-unemployment-websites-that-didnt-work/"))
        assertThat(article.urlToImage,`is`("https://img-cdn.tnwcdn.com/image/tnw?filter_last=1&fit=1280%2C640&url=https%3A%2F%2Fcdn0.tnwcdn.com%2Fwp-content%2Fblogs.dir%2F1%2Ffiles%2F2020%2F07%2FUntitled-design-4-2.png&signature=b689851f38d06adfd848dd12e8abe5fb"))
        assertThat(article.publishedAt,`is`("2020-07-19T17:00:55Z"))

    }


}
