package com.awais.api_service

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ApiService(baseUrl: String) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
    
    suspend fun <T> request(call: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                val data = response.body()
                ApiResponse(data, null)
            } else {
                ApiResponse(null, Exception("Request failed with code: ${response.code()}"))
            }
        } catch (e: IOException) {
            ApiResponse(null, Exception("Request failed: ${e.message}"))
        }
    }
}

data class ApiResponse<T>(
    val data: T?,
    val error: Exception?
)
//
//interface ApiInterface {
//    @GET("someEndpoint")
//    suspend fun getSomeData(): Response<YourDataType>
//}
//
//// Example usage with coroutines:
//val baseUrl = "https://api.example.com/"
//val apiService = ApiService(baseUrl)
//val api = apiService.createService(ApiInterface::class.java)
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
