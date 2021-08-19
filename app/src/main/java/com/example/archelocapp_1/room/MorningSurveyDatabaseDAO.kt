package com.example.archelocapp_1.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.archelocapp_1.Models.MorningSurvey

@Dao
interface MorningSurveyDatabaseDAO {

    @Insert
    fun insert(moringSurvey: MorningSurvey)

    @Insert
    fun update(moringSurvey: MorningSurvey)

    @Query("SELECT * from Morning_survey_table WHERE nest_id = :key")
    fun get(key: Long): MorningSurvey?

    @Query("DELETE FROM Morning_survey_table")
    fun clear()

    @Query("SELECT * FROM Morning_survey_table ORDER BY nest_id DESC LIMIT 1")
    fun getrecentSurvey(): MorningSurvey?

    @Query("SELECT * FROM Morning_survey_table ORDER BY nest_id DESC")
    fun getAllSurvey(): LiveData<List<MorningSurvey>>

}