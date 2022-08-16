package com.example.news_app.web_services

import com.example.news_app.domain.model.News
import com.example.news_app.domain.sealed.State
import com.example.news_app.util.Constants
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Client {
    private val client = OkHttpClient()

    fun getNewsData(): State<News> {
        val request = Request.Builder().url(getNewsUrl()).build()
        val response =  client.newCall(request).execute()
        return if (response.isSuccessful){

            val result = Gson().fromJson( response.body?.string(), News::class.java)
            State.Success(result)

        } else{
            State.Error(response.message)
        }
    }

    private fun getNewsUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(Constants.Api.PROTOCOL)
            .host(Constants.Api.HOST)
            .addPathSegments("v2/everything")
            .addQueryParameter(Constants.Api.Q, "tesla")
            .addQueryParameter(Constants.Api.FROM, "2022-07-14")
            .addQueryParameter(Constants.Api.SORT_BY, "publishedAt")
            .addQueryParameter(Constants.Api.API_KEY, Constants.Api.KEY)
            .build()
    }

}