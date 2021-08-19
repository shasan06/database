package com.example.archelocapp_1

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.archelocapp_1.Models.MorningSurvey
import com.example.archelocapp_1.room.MorningSurveyDatabase
import com.example.archelocapp_1.room.MorningSurveyDatabaseDAO
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws


@RunWith(AndroidJUnit4::class)
class MorningSurveyDatabaseTest {

    private lateinit var morningSurveyDao: MorningSurveyDatabaseDAO
    private lateinit var db: MorningSurveyDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        //Using an in-memory database because the information stored here disappears when the
        //process is killed
        db = Room.inMemoryDatabaseBuilder(context, MorningSurveyDatabase::class.java)
            .allowMainThreadQueries()
            .build()//allowing the main thread queries just for testing
        morningSurveyDao = db.morningSurveyDatabaseDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insert(){
        runBlocking { val morningSurvey = MorningSurvey()
            morningSurveyDao.insert(morningSurvey)
            val morningSurveydao = morningSurveyDao.getrecentSurvey()
            Assert.assertEquals(morningSurveydao?.non_nesting_attempts, "-1")
            }
    }
}


//Some of the problems faced by the kapt dependencies in android studio  that took a lot of the time
//problem 1

//having alot of kapt issues if I use kotlin version 1.4 it will give error as below
//kapt error Execution failed for task ':app:kaptDebugKotlin'.
////> A failure occurred while executing org.jetbrains.kotlin.gradle.internal.KaptExecution
////   > java.lang.reflect.InvocationTargetException (no error message)

//Solution to problem 1 as per https://forums.bignerdranch.com/t/solved-execution-failed-for-task-kaptdebugkotlin/17431/4
// downgrade the  kotlin version from buildscript.ext.kotlin_version=“1.4.21” to “1.3.21 inorder to resolve

//Problem 2
//But if I downgrade Problem 2 arises
//kapt\morning-survey-app\app\src\main\java\com\example\archelocapp_1\others\StartSurvey.kt: (46, 14): 'also((T) -> Unit): T' is only available
// since Kotlin 1.3.50 and cannot be used in Kotlin 1.3
//that is unit testing is not supported

//Solution to problem 2 used the version 1.3.50 in the build. gradle project that supports both


//Problem 3
//e: C:\Users\Sabrina1\Desktop\App work\morning
//
//survey testing for kapt\morning-survey-app\app\src\main\java\com\example\archelocapp_1\ui\LoginFragment.kt:
//(50, 44): Unresolved reference: of