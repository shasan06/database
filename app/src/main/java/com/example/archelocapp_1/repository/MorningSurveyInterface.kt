package com.example.archelocapp_1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.archelocapp_1.Models.MorningSurvey

interface MorningSurveyInterface {

    fun insert(moringSurvey: MorningSurvey)

    fun update(morningSurvey: MorningSurvey)

    fun get(key: Long): MorningSurvey?

    fun clear()

    fun getrecentSurvey(): MorningSurvey?

    fun getAllSurvey(): LiveData<List<MorningSurvey>>

}