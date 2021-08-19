package com.example.archelocapp_1.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.archelocapp_1.R
import com.example.archelocapp_1.databinding.ActivityStartSurveyBinding
import com.example.archelocapp_1.repository.MorningSurveyRepository
import com.example.archelocapp_1.room.MorningSurveyDatabase
import com.example.archelocapp_1.room.MorningSurveyDatabase.Companion.getInstance
import com.example.archelocapp_1.room.MorningSurveyDatabaseDAO
import com.example.archelocapp_1.viewmodels.MorningSurveyViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

//Morning survey fragment
class StartSurveyFragment : Fragment() {

    //Instantiate required objects / classes
    private lateinit var binding: ActivityStartSurveyBinding
    private val morningSurveyViewModel: MorningSurveyViewModel by viewModels()
    lateinit var database: MorningSurveyDatabase
    var morningSurveyDao: MorningSurveyDatabaseDAO? = null //interface
    private var viewModelJob = Job()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityStartSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val database by lazy { MorningSurveyDatabase.getInstance(requireContext()) }
       // val repository by lazy { MorningSurveyRepository(database)}

        init()

        listeners()

        requestUserPermission()
        database = getInstance((requireActivity().applicationContext))
        loadDb(requireActivity().applicationContext)

        val morningSurveyRepository = MorningSurveyRepository(requireNotNull(database))
        morningSurveyViewModel.provideDatabase(morningSurveyRepository)
    }





    // Create DB instance if it does not already exist
    fun loadDb(context: Context) {
        if (database == null) {


            database = Room.databaseBuilder(
                context,
                MorningSurveyDatabase::class.java,
                "Morning_Survey_DB"
            )
                .build()
        }

        morningSurveyDao = database!!.morningSurveyDatabaseDAO()

    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun listeners() {
        binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }
        //when a submit button on screen 3 is clicked it will submit the user data (the fields below) into database
        binding.submit.setOnClickListener {
            //1 Area field taking data from ui id areatext and storing into a variable area
            val area = binding.AreaText.text.toString()
            //Setingup Calendar to collect date selected from date Picker
            val day: Int = binding.datePicker.getDayOfMonth()
            val month: Int = binding.datePicker.getMonth()
            val year: Int = binding.datePicker.getYear()
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val formatedDate: String = sdf.format(calendar.getTime())
            //2 Date
            val date = formatedDate
            //3 Beach
            val beach = binding.BeachText.text.toString()
            //4 sector
            val sector = binding.SectorText.text.toString()
            //5 subsector
            val subsector = binding.SubSectorText.text.toString()
            //6 emergence_event string
            val emergence_event = binding.EmergenceEventText.text.toString()
            //7 nest
            var nest: String = binding.NestText.text.toString()
            //8 distance_to_sea
            val distance_to_sea = binding.DistancetoSeaText.toString()
            //9 track_type
            val track_type = binding.TrackTypeText.text.toString()
            //10 non_nesting_attempts
            val non_nesting_attempts = binding.NonNestingAttemptsText.text.toString()
            //11 tags
            val tags = binding.tagsText.text.toString()
            //12 comments
            val comment = binding.CommentsText.text.toString()
            //13 photo_id
            val photo_id = binding.photoIDText.text.toString()
            // Get location from fusded location provider for survey location
            var gps_latitude = ""
            var gps_longitude = ""
            //it takes user permission to store its location.
            if (ActivityCompat.checkSelfPermission(
                    this.requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this.requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            morningSurveyViewModel.fusedLocationClient?.lastLocation
                ?.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        gps_latitude = location.latitude.toString()
                        gps_longitude = location.longitude.toString()
                    }
                }


            //val morningSurvey: MorningSurvey = MorningSurvey() can pass this object
            morningSurveyViewModel.submitData(
                area = area,
                dateData = date,
                beach = beach,
                sector = sector,
                subsector = subsector,
                emergence_event = emergence_event,
                nest = nest,
                distance_to_sea = distance_to_sea,
                track_type = track_type,
                non_nesting_attempts = non_nesting_attempts,
                tags = tags,
                comment = comment,
                photo_id = photo_id,
                gps_latitude = gps_latitude,
                gps_longitude = gps_longitude
            )
        }
        //the next button will take a user to the next screen
        binding.nextButton.setOnClickListener {
            findNavController().navigate(StartSurveyFragmentDirections.actionStartSurveyFragmentToObserverFragment())
        }
        //On clicking the plus button the data to be submitted in the add morning survey method of the
        //suspectedNestViewModelclass
//        binding.plusButton.setOnClickListener {
//            // to bind the data of the calender
//            val cal = binding.calendarView2.date.toString()
//            //to bind the time
//            val time = binding.timePicker1.hour.toString() +" : "+ binding.timePicker1.minute.toString()
//
//            val spinnerdata1 = binding.spinner.selectedItem.toString()
//            val spinnerdata2 = binding.spinner2.selectedItem.toString()
//            suspectedNestViewModel.addMorningSurvey(cal, time, spinnerdata1, spinnerdata2)
//            //the timer mode
//
//        }
    }





    private fun requestUserPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return true
        }
        return false
    }

    private fun init() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerItems,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                //binding.spinner.adapter = adapter
            }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerItems1,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                //binding.spinner2.adapter = adapter
            }
    }


}