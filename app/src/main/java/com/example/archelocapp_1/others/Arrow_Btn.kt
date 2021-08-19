package com.example.archelocapp_1.others

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.archelocapp_1.R

class Arrow_Btn : AppCompatActivity() {

    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrow__btn)

        button3

        //we got to create an intent that includes the class called CheatActivity which is a new frament
        button3.setOnClickListener {
            //startActivity(Intent(this@MainActivity, Arrow_Btn::class.java))

            // Also like this

            val intent = Intent(this@Arrow_Btn, StartSurvey::class.java)
            startActivity(intent)
        }
    }
}
