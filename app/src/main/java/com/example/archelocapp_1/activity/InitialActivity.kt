package com.example.archelocapp_1.activity

import android.Manifest.permission
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import com.example.archelocapp_1.R

class InitialActivity : AppCompatActivity(R.layout.activity_initial){
    private  val TAG = "InitialActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ActivityCompat.requestPermissions(this, arrayOf(
                permission.CAMERA,
                permission.READ_EXTERNAL_STORAGE,
                permission.ACCESS_FINE_LOCATION,
                permission.ACCESS_COARSE_LOCATION
            ), 100)


    }

    override fun onBackPressed() {
        super.onBackPressed()

        Log.d(TAG, "onBackPressed: " + findNavController(R.id.fragment).currentDestination?.id)




    }



}