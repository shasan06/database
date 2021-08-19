package com.example.archelocapp_1.others

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.archelocapp_1.R

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    // THE VARIABLES HAVE BEEN DECLARED DOWN
    private lateinit var arrowButton:ImageButton
    private lateinit var startSurvey: StartSurvey
    private lateinit var usernameView:TextView
    private lateinit var passwordView:TextView
    //@SuppressLint("WrongViewCast")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        arrowButton = findViewById(R.id.arrowBtn) as ImageButton
        //get the username and password
        usernameView = findViewById(R.id.UsernameBtn) as TextView
        passwordView = findViewById(R.id.PasswordBtn) as TextView

        //we got to create an intent that includes the class called CheatActivity which is a new frament
        arrowButton.setOnClickListener {
            //startActivity(Intent(this@MainActivity, Arrow_Btn::class.java))

            //now pass the username and password to the cons
            /*var username = usernameView.text.toString()
            var password = passwordView.text.toString()

            apiRepo= ApiRepository(username, password)*/

            // Also like this

            val intent = Intent(this@MainActivity, Arrow_Btn::class.java)
            startActivity(intent)
        }



    }
}
