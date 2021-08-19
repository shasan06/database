package com.example.archelocapp_1.utils

import android.content.Context
import android.content.SharedPreferences

class Preference constructor(context: Context) {


      companion object{
          const val EMERGENCY_KEY = "emergency_key"
          const val EMERGENCY_NO = "emergency_no"
      }

    private var pref: SharedPreferences = context.getSharedPreferences("my_preference", Context.MODE_PRIVATE)


    fun setData(key: String, value: String){
        pref.edit().apply {
            putString(key, value)
        }.apply()
    }

    fun getData(key: String) = pref.getString(key, "")

    fun clear(){
        pref.edit().clear().apply()
    }


}