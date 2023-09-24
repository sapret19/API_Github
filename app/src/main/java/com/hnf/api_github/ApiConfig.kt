package com.hnf.api_github

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiConfig {

    const val BASE_URL = "https://api.github.com/"

    val apiService: ApiService
        get() {
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", "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")
                    .build()
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
//fun getRetrofit(): Retrofit{
//    return Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//}
//
//    fun getService(): ApiService{
//        return getRetrofit().create(ApiService::class.java)
//    }
}