package com.example.archelocapp_1.Models

import com.squareup.moshi.Json

//Login is the model class that takes user credential in the form of json data
data class Login(
    @field:Json(name = "key") var key: String
)




