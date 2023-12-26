package com.awais.android.networking

import com.awais.android.core.BASEURLS
import com.awais.android.core.EndPoints
import com.awais.android.core.NEWS_API_KEY
import com.awais.android.features.news.domains.models.NewsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiClient {
    @GET(EndPoints.GET_NEWS)
    suspend fun getNewsArticles(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String,
    ): Response<NewsResponse>
    
    companion object {
        private const val BASE_URL = BASEURLS.NEWS_BASE_URL
        private const val API_KEY = NEWS_API_KEY
        
        fun create(): NewsApiClient {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            
            return retrofit.create(NewsApiClient::class.java)
        }
    }
}
