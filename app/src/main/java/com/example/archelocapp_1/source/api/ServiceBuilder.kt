package com.example.archelocapp_1.source.api

import android.util.Log
import com.example.archelocapp_1.source.api.ServiceBuilder.KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {

    var KEY : String = ""

    var httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("REQUEST==  ", message)
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY)

    private var client = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(httpLoggingInterceptor)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("http://archaelon.roussos.mobi/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    fun getClient() : ApiService{
        return retrofit.create(ApiService::class.java)
    }

}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request().newBuilder()
                .addHeader("Authorization", KEY)
                .build()
        )
    }
}

