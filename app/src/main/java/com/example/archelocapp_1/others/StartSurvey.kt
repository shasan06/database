package com.example.archelocapp_1.others

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.archelocapp_1.R


class StartSurvey : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_survey)

        //val mySpinner = findViewById(R.id.spinner) as Spinner
        val spinner: Spinner = findViewById(R.id.spinner)

        //val mySpinner2 = findViewById(R.id.spinner) as Spinner
        val spinner2: Spinner = findViewById(R.id.spinner2)

        //val previous button
        val previous: ImageButton = findViewById(R.id.previous_Button)

        //set timepicker mode
        //OnClickTime()

//        //plus button start new survey
//        val plusButton: ImageButton = findViewById((R.id.plus_Button))
//        //we got to create an intent that includes the class called CheatActivity which is a new frament
//        plusButton.setOnClickListener {
//            //startActivity(Intent(this@MainActivity, Arrow_Btn::class.java))
//
//            // Also like this
//
//            val intent = Intent(this@StartSurvey, StartingNewSurvey::class.java)
//            startActivity(intent)
//        }


        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.spinnerItems,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        ////////////////////////////////
        ArrayAdapter.createFromResource(
            this,
            R.array.spinnerItems1,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner2.adapter = adapter
            }

        previous.setOnClickListener(){
            onBackPressed()
        }








    }

//    //first define the function OnClickTime() and the two variables
//    fun OnClickTime(){
//        //val textView = findViewById<TextView>(R.id.textView)
//        val timePicker = findViewById<TimePicker>(R.id.timePicker1)
//        timePicker.setOnTimeChangedListener{ _, hour, minute -> var hour = hour
//            var am_pm = ""
//            //AM_PM decider logic
//            when{hour == 0 -> { hour += 12
//                am_pm = "AM"
//            }
//                hour == 12 -> am_pm = "PM"
//                hour > 12 -> {hour-=12
//                    am_pm = "PM"
//                }
//                else -> am_pm = "AM"
//
//            }
//
//        }
//    }

    //plus button click



}
