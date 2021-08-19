package com.example.archelocapp_1.source.api

import androidx.lifecycle.LiveData
import com.example.archelocapp_1.Models.GeographicalData
import com.example.archelocapp_1.Models.Login
import com.example.archelocapp_1.Models.MorningSurvey
import com.example.archelocapp_1.Models.Nest
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login/")
    suspend fun getData(
        @Field("username") userName : String,
        @Field("password") password : String
    ) : Response<Login>


    @POST("api/archelon/nest_list")
    suspend fun addNest(
        @Body data: HashMap<String, Any>
    ) : Response<Nest>

    @POST("api/archelon/nest_data")
    suspend fun addGeographicalData(
        @Body data: HashMap<String, Any>
    ) : Response<GeographicalData>


   @POST("api/archelon/morning_survey")
    suspend fun addMorningSurvey(
        @Body data: HashMap<String, Any>
    ) : Response<MorningSurvey>






}