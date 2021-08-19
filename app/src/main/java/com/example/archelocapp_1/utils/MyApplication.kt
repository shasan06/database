package com.example.archelocapp_1.utils

import android.app.Application
import com.example.archelocapp_1.repository.MorningSurveyRepository
import com.example.archelocapp_1.room.MorningSurveyDatabase
import com.example.archelocapp_1.room.MorningSurveyDatabaseDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts


}