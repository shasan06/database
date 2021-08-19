package com.example.archelocapp_1.repository



import android.content.Context
import androidx.lifecycle.LiveData
import com.example.archelocapp_1.Models.MorningSurvey
import com.example.archelocapp_1.room.MorningSurveyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class MorningSurveyRepository(val morningsurveydatabase: MorningSurveyDatabase ) : MorningSurveyInterface {
//define a method insert to store user data
    override fun insert(morningSurvey: MorningSurvey) {
        CoroutineScope(Dispatchers.IO).launch {
            morningsurveydatabase.morningSurveyDatabaseDAO().insert(morningSurvey)
        }

    }
    //define a method update to update user data
    override fun update(moringSurvey: MorningSurvey) {
        CoroutineScope(Dispatchers.IO).launch {
            morningsurveydatabase.morningSurveyDatabaseDAO().update(moringSurvey)
        }
    }

    override fun get(key: Long): MorningSurvey? {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun getrecentSurvey(): MorningSurvey? {
        TODO("Not yet implemented")
    }

    override fun getAllSurvey(): LiveData<List<MorningSurvey>> {
        TODO("Not yet implemented")
    }


}