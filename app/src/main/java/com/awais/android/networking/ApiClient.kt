package com.awais.android.networking

import com.awais.api_service.ApiResponse
import retrofit2.http.GET

interface ApiClient {
    @GET("someEndpoint")
    suspend fun getSomeData(): ApiResponse<String>
}

//// Example usage with coroutines:
//val baseUrl = "https://api.example.com/"
//val apiService = ApiService(baseUrl)
//val api = apiService.createService(ApiClient::class.java)
//
//suspend fun getSomeData() = apiService.request { api.getSomeData() }
//if (response.error != null) {
//    // Handle error
//    println("Error: ${response.error.message}")
//} else {
//    val data = response.data
//    // Handle data
//    println("Received data: $data")
//}