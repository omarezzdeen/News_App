package com.example.news_app.web_services

import com.example.news_app.domain.model.News
import com.example.news_app.domain.sealed.State
import com.example.news_app.util.Constants
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object Client {
    private val client = OkHttpClient()

    fun getNewsData(): State<News> {
        val request = buildRequest()
        val response = getResponse(request)
        return checkStatusCode(response)
    }

    private fun buildRequest() = Request.Builder().url(getNewsUrl()).build()

    private fun getResponse(request: Request) = client.newCall(request).execute()

    private fun checkStatusCode(response: Response) = if (response.isSuccessful) {
        val result = Gson().fromJson(response.body?.string(), News::class.java)
        State.Success(result)
    } else {
        State.Error(response.message)
    }


    private fun getNewsUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(Constants.Api.PROTOCOL)
            .host(Constants.Api.HOST)
            .addPathSegments("v2/everything")
            .addQueryParameter(Constants.Api.Q, "apple")
            .addQueryParameter(Constants.Api.SORT_BY, "publishedAt")
            .addQueryParameter(Constants.Api.API_KEY, Constants.Api.KEY)
            .build()
    }

}