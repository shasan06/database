package com.example.archelocapp_1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.archelocapp_1.Models.MorningSurvey

@Database(entities = [MorningSurvey::class], version = 1, exportSchema = false)
abstract class MorningSurveyDatabase : RoomDatabase() {

    abstract fun morningSurveyDatabaseDAO(): MorningSurveyDatabaseDAO

    companion object {

        @Volatile
        private var INSTANCE: MorningSurveyDatabase? = null

        fun getInstance(context: Context): MorningSurveyDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MorningSurveyDatabase::class.java,
                        "Morning_survey_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}