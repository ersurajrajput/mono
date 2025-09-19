package com.ersurajrajput.mono.api

import com.ersurajrajput.mono.data.model.TweetModel
import kotlinx.coroutines.time.delay
import retrofit2.Response
import retrofit2.http.GET

interface TweetsyAPI {

    @GET("tweets.json")
    suspend fun getTweets(): Response<List<TweetModel>>

}