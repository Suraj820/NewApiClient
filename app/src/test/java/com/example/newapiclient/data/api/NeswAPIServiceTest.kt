package com.example.newapiclient.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NeswAPIServiceTest {
    private lateinit var service:NewsAPIService
    private lateinit var server:MockWebServer

    @Before
    fun setUp() {
       server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }



    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
        runBlocking {

            enqueueMockResponse("newsresponse.json")
            val responseBody =service.getTopHeadlines("us",1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=204b409bd3f74b2a902fd73cb750a49b")
        }
    }

    @Test
    fun getTopHEadlines_receivedResponse_correctPageSize() {
        runBlocking {

            enqueueMockResponse("newsresponse.json")
            val responseBody =service.getTopHeadlines("us",1).body()
            val articlesList = responseBody!!.articles
            assertThat(articlesList.size).isEqualTo(20)

            }
    }

    @Test
    fun getTopHEadlines_receivedResponse_correctContent() {
        runBlocking {

            enqueueMockResponse("newsresponse.json")
            val responseBody =service.getTopHeadlines("us",1).body()
            val articlesList = responseBody!!.articles
            val article = articlesList[0]
            assertThat(article.author).isEqualTo("Bryan Pietsch")
            assertThat(article.url).isEqualTo("https://www.washingtonpost.com/business/2023/04/04/johnson-baby-powder-talc-settlement-cancer/")
            assertThat(article.publishedAt).isEqualTo("2023-04-05T04:11:06Z")



        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}