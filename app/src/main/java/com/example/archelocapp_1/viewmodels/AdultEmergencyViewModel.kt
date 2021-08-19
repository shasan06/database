package com.example.archelocapp_1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archelocapp_1.Models.Nest
import com.example.archelocapp_1.repository.Repository
import com.example.archelocapp_1.utils.Preference
import kotlinx.coroutines.launch
import java.util.HashMap

class AdultEmergencyViewModel : ViewModel() {
    private var repository = Repository()

    fun checkData(data: String, preference: Preference): Boolean {
        val value = preference.getData(Preference.EMERGENCY_KEY)
        if (value.isNullOrEmpty()) {
            return true
        } else {
            val split = value.split(",")
            split.forEach {
                if (data == it) {
                    return false
                }
            }
            return true
        }

    }

    fun saveData(saveContact: String, preference: Preference) {
        var value = preference.getData(Preference.EMERGENCY_KEY)
        value = if (value.isNullOrEmpty()){
            saveContact
        }else{
            "$value,$saveContact"
        }
        preference.setData(Preference.EMERGENCY_KEY, value)
    }

    fun getEmergencyNo(preference: Preference) : Int {
        val data = preference.getData(Preference.EMERGENCY_NO)
        return if (data.isNullOrEmpty()) 0 else data.toInt()
    }


    fun addNest(nestCode : String) {
        viewModelScope.launch {
            val data = HashMap<String, Any>()
            data["nest_code"] = nestCode
            data["beach"] = "MAVROUNI"
            data["sector"] = "WEST"
            data["subsector"] = "1200"
            data["protection_measures"] = "MS"
            data["inundated_during_incubation"] = true
            data["predated_during_incubation"] = false
            data["date_of_first_hatching"] = "2020-11-27"
            data["date_of_last_hatching"] = "2020-11-27"
            data["inundated_during_hatching"] = true
            data["predated_during_hatching"] = false
            data["affected_by_light_pollution"] = true
            data["excavation_date"] = "2020-11-20"
            data["excavation_bottom_of_nest_depth"] = "2020-11-20"
            data["hatched_eggs"] = 3
            data["pipped_dead_hatchlings"] = 2
            data["pipped_live_hatchlings"] = 4
            data["no_embryos_in_unhatched_eggs"] = 5
            data["dead_embryos_in_unhatched_eggs_eye_spot"] = 4
            data["dead_embryos_in_unhatched_eggs_early"] = 5
            data["dead_embryos_in_unhatched_eggs_middle"] = 7
            data["dead_embryos_in_unhatched_eggs_late"] = 9
            data["live_embryos_in_unhatched_eggs"] = 6
            data["dead_hatchlings_in_nest"] = 3
            data["live_hatchlings_in_nest"] = 5
            data["excavation_comments"] = "Test comments"
            data["general_comments"] = "More test comments"

            val response = repository.addNest(data)
            if (response.isSuccessful) {
                var nest = response.body() as Nest
            }
        }
    }


}