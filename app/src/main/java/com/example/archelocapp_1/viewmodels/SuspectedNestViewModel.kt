package com.example.archelocapp_1.viewmodels

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.widget.Spinner
import android.widget.TimePicker
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archelocapp_1.Models.GeographicalData
import com.example.archelocapp_1.Models.MorningSurvey
import com.example.archelocapp_1.Models.Nest
import com.example.archelocapp_1.repository.Repository
import com.example.archelocapp_1.utils.AuthListener
import com.example.archelocapp_1.utils.LocationTrack
import com.example.archelocapp_1.utils.Preference
import kotlinx.coroutines.launch
import java.util.*

class SuspectedNestViewModel : ViewModel() {

    private var repository = Repository()

    fun getLat(requireContext: Context) : String{
        val locationTrack = LocationTrack(requireContext)
      return  "${locationTrack.getLatitude()}"
    }

    fun getLng(requireContext: Context) : String{
        val locationTrack = LocationTrack(requireContext)
        return  "${locationTrack.getLongitude()}"
    }

    fun getPlace(requireContext: Context): Address {
        val loc = Geocoder(requireContext, Locale.getDefault())
        val locationTrack = LocationTrack(requireContext)
         return loc.getFromLocation(locationTrack.getLatitude(), locationTrack.getLongitude(), 1)[0]
    }

    fun addEmergencyNo(requireContext: Context) {
        val preference = Preference(requireContext)
        val emergencyNo = preference.getData(Preference.EMERGENCY_NO)
        if (!emergencyNo.isNullOrEmpty()){
            preference.setData(Preference.EMERGENCY_NO, "${emergencyNo.toInt().inc()}")
        }else{
            preference.setData(Preference.EMERGENCY_NO, "1")
        }
    }

    //
    fun addGeographicalData(gps_latitude: String, gps_longitude: String, distanceToSea: String, listener: AuthListener<Boolean>){
        viewModelScope.launch {
            val data = HashMap<String, Any>()
            data["gps_latitude"] = gps_latitude
            data["gps_longitude"] = gps_longitude
            data["nest"] = 1
            data["date"] = "2020-11-27"
            data["start_time"] = "2020-11-27T12:49:44.164000Z"
            data["end_time"] = "2020-11-27T12:49:49.655000Z"
            data["relocated_to"] = "BoB"
            data["reason_for_relocation"] = "I"
            data["top_egg_depth"] = 22
            data["bottom_of_nest_depth"] = 40
            data["distance_to_sea"] = distanceToSea.toLong()
            data["number_of_eggs"] = 91

            val response = repository.addGeographicalData(data)
            if (response.isSuccessful){
                listener.onSuccess(true)
                var geographicalData = response.body() as GeographicalData
            }else{
                listener.onSuccess(false)
            }
        }
    }

//the api does not have fields to post all the data of the aap, so I tried posting some of them

    fun addMorningSurvey(cal: String, time: String, spinnerdata1: String, spinnerdata2: String){
        viewModelScope.launch {
            val data = HashMap<String, Any>()
            data["area"] = "LAK"
            data["date"] = cal
            //create a time
            //data["time"] = time
            data["beach"] = spinnerdata1
            data["sector"] = spinnerdata2
            data["subsector"] = "fddd"
            data["emergence_event"] = "HD*"
            data["nest"] = 1
            data["distance_to_sea"] = 2
            data["track_type"] = "1"
            data["non_nesting_attempts"] = 55
            data["gps_latitude"] = 34.547562
            data["gps_longitude"] = 22.547562
            data["tags"] = "df"
            data["comments"] = "Test"
            data["photo_id"] = "myphoto"
            data["nest_id"] = 1
            val addMorningSurvey = repository.addMorningSurvey(data)
            if (addMorningSurvey.isSuccessful){
                var morningSurvey = addMorningSurvey.body() as MorningSurvey
            }
            else{
                val message = addMorningSurvey.errorBody()
                print(message)

            }
        }
    }



}