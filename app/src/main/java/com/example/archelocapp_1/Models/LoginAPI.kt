package com.example.archelocapp_1.Models

import android.os.Bundle
import androidx.navigation.NavDirections

import com.example.archelocapp_1.R
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.lang.Boolean.FALSE
import java.util.*


// This support functions and class to setup Login API request
private val BASE_URL =
    "http://archaelon.roussos.mobi/"

private val logging = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY);

private val httpClient = OkHttpClient.Builder()
    .addInterceptor(logging)
    .followRedirects(FALSE)
    .followSslRedirects(FALSE)
    .build();

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(httpClient)
    .build()


// This defines API endpoints(urls)
interface LoginApiService {
    //to submit the authenticated user credentials through post method to curl location
    //request post to http://archaelon.roussos.mobi/login/
    @POST("login/")
    fun authenticateUser(@Body body: MultipartBody):
            Deferred<Login>

}

object LoginApi {
    val retrofitService : LoginApiService by lazy {
        retrofit.create(LoginApiService::class.java) }
}




