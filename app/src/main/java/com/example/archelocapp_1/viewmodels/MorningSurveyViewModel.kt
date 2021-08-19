package com.example.archelocapp_1.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.archelocapp_1.Models.MorningSurvey
import com.example.archelocapp_1.repository.MorningSurveyInterface
import com.example.archelocapp_1.repository.MorningSurveyRepository
import com.example.archelocapp_1.room.MorningSurveyDatabase
import com.example.archelocapp_1.room.MorningSurveyDatabaseDAO
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.FusedLocationProviderClient

class MorningSurveyViewModel(val surveyrepo: MorningSurveyInterface) : ViewModel() {

    //declaring the object
    var fusedLocationClient: FusedLocationProviderClient? = null
    //var morningSurveyModel = MorningSurvey()//object for morning survey model
    //var db: MorningSurveyDatabase? = null  //room database
    //var morningSurveyDao: MorningSurveyDatabaseDAO? = null //interface
    var morningSurveyRepository: MorningSurveyRepository? = null //interface

// init {
//     loadDb(context)
//     morningSurveyRepository = MorningSurveyRepository(requireNotNull(db))
// }
    // Creating an instance of the database if it does not already exist

//    fun loadDb( context: Context) {
//        if (db == null) {
//            db = Room.databaseBuilder(context,MorningSurveyDatabase::class.java,"Morning_Survey_DB")
//                .build()
//        }
//        morningSurveyDao = db!!.morningSurveyDatabaseDAO()
//
//    }

    // get the latest available location to be recorded for morning survey(used in start survey fragment)

    fun loadLocationProvider(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    //function for inserting the data into database
    private fun insert(
        morningSurvey: MorningSurvey
    ) {
        surveyrepo.insert(morningSurvey)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("StartSurvey", "StartSurveyFragment destroyed!")
    }

    fun submitData(
        area: String,
        dateData: String,
        beach: String,
        sector: String,
        subsector: String,
        emergence_event: String,
        nest: String,
        distance_to_sea: String,
        track_type: String,
        non_nesting_attempts: String,
        tags: String,
        comment: String,
        photo_id: String,
        gps_latitude: String,
        gps_longitude: String
    ) {
        morningSurveyRepository?.insert(MorningSurvey(area, dateData, beach, sector, subsector,
            emergence_event, nest, distance_to_sea, track_type, non_nesting_attempts, tags,
            comment, photo_id, gps_latitude, gps_longitude)) ?: IllegalArgumentException()
    }

    fun provideDatabase(repository: MorningSurveyRepository) {
        morningSurveyRepository = repository
    }

}
//to store the data
//the flow of data is from fragment (ui) to viewmodel to repository to dao interface and that will submit data into the database
//the sequence is given below
//ui(Start Survey Fragment)-->viewmodel(Morning SurveyViewModel)-->repository(Morning Survey Repository)--->dao interface(Morning Survey Database DAO) will be inserting to the room database
// to get the data from database the sequence is given below
//ui-vm-repo-dao-repo-vm-fragm