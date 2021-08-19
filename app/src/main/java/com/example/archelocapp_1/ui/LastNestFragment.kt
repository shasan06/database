package com.example.archelocapp_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.databinding.ActivityLastLayoutBinding
import com.example.archelocapp_1.utils.AuthListener
import com.example.archelocapp_1.utils.DrawingView
import com.example.archelocapp_1.utils.MyDialog
import com.example.archelocapp_1.viewmodels.SuspectedNestViewModel

class LastNestFragment : Fragment() {


    private lateinit var binding: ActivityLastLayoutBinding
    private val suspectedNestViewModel : SuspectedNestViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityLastLayoutBinding.inflate(inflater, container, false)
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

        binding.submit.setOnClickListener {
            suspectedNestViewModel.addEmergencyNo(requireContext())
            val lat = suspectedNestViewModel.getLat(requireContext())
            val lng = suspectedNestViewModel.getLat(requireContext())


            val distanceToSea = binding.distanceToSea.text.toString()

            suspectedNestViewModel.addGeographicalData(
                lat,
                lng,
                distanceToSea,
                object : AuthListener<Boolean> {
                    override fun onSuccess(v: Boolean) {
                        if (v) {
                            findNavController().navigate(LastNestFragmentDirections.actionLastNestFragmentToNewSurveyFragment())
                        }
                    }

                })
        }

        binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cancel.setOnClickListener {
            MyDialog(requireContext()).cancel {
                binding.cancel.setOnClickListener {
                    findNavController().navigate(LastNestFragmentDirections.actionLastNestFragmentToAddNewEvent())
                }
            }
        }

    }

}