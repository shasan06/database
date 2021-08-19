package com.example.archelocapp_1.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.archelocapp_1.Models.Login
import com.example.archelocapp_1.R
import com.example.archelocapp_1.source.api.ServiceBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Demo : AppCompatActivity(){

    private val TAG = "Demo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dummy)





        Log.d(TAG, "onCreate: Hi 1 ")
   /*   GlobalScope.launch {
          val data = ServiceBuilder.getClient().getData("shasan06", "^dusk|FULL|HIGH^")
          var login = data.body() as Login
          Log.d(TAG, "onCreate: ${data.body()}")

      }*/
        Log.d(TAG, "onCreate: Hi 2 ")





    }
}