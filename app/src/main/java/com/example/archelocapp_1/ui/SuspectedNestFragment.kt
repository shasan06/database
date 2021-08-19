package com.example.archelocapp_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.databinding.FragmentNestPlaceBinding
import com.example.archelocapp_1.utils.AuthListener
import com.example.archelocapp_1.utils.DrawingView
import com.example.archelocapp_1.utils.MyDialog
import com.example.archelocapp_1.utils.Preference
import com.example.archelocapp_1.viewmodels.SuspectedNestViewModel
import kotlinx.android.synthetic.main.fragment_adult_emergence.view.*
import kotlinx.android.synthetic.main.fragment_nest__place.view.*

class SuspectedNestFragment : Fragment() {

    private lateinit var binding: FragmentNestPlaceBinding
    private val suspectedNestViewModel: SuspectedNestViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNestPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        init()
        onclick()


    }

    private fun init() {


        val lat = suspectedNestViewModel.getLat(requireContext())
        val lng = suspectedNestViewModel.getLat(requireContext())
        val place = suspectedNestViewModel.getPlace(requireContext())
        binding.latLng.setText("$lat - $lng")
        binding.location.setText(place.locality)
        binding.drawView.addView(DrawingView(requireContext()))
    }

    private fun onclick() {
        binding.checkBoxNo.setOnClickListener {
            if (binding.checkBoxNo.isChecked) {
                findNavController().navigate(SuspectedNestFragmentDirections.actionSuspectedNestFragmentToLastNestFragment())
            }


        }







        binding.previousButton.setOnClickListener {
           findNavController().popBackStack()
        }

        binding.submit.setOnClickListener {
            suspectedNestViewModel.addEmergencyNo(requireContext())
            //findNavController().navigate(SuspectedNestFragmentDirections.actionSuspectedNestFragmentToNewSurveyFragment())
            //add the data to the nest when the user ticks Yes and clicks the submit button
            val dug_checkYes = binding.dugCheckYes.checkBoxYes
            val dug_checkNo = binding.dugCheckYes.checkBoxNo
            val trackdata = binding.Trackany
            val option = binding.options
            val nextdiag = binding.drawView
            val distancetosea = binding.distancetosea.toString()
            //these lat, lon i just got from above------
            val lat = suspectedNestViewModel.getLat(requireContext())
            val lng = suspectedNestViewModel.getLat(requireContext())


            //post the data to the method
            suspectedNestViewModel.addGeographicalData(
                    distancetosea,
                    lat,
                    lng,
                    //what is the result of the auth listener
                    object : AuthListener<Boolean> {
                        override fun onSuccess(v: Boolean) {
                            if (v) {
                                findNavController().navigate(LastNestFragmentDirections.actionLastNestFragmentToNewSurveyFragment())
                            }
                        }

                    })
        }

        binding.cancel.setOnClickListener {
            MyDialog(requireContext()).cancel {
                binding.cancel.setOnClickListener {
                    findNavController().navigate(SuspectedNestFragmentDirections.actionSuspectedNestFragmentToAddNewEvent())
                }
            }
        }


    }



}