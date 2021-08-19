package com.example.archelocapp_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.R
import com.example.archelocapp_1.databinding.ActivityStartingNewSurveyBinding
import com.example.archelocapp_1.utils.MyDialog

class ObserverFragment : Fragment(){

    private lateinit var binding: ActivityStartingNewSurveyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ActivityStartingNewSurveyBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        onClick()
        addSpinners()

    }

    private fun addSpinners() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner3,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner1.adapter = adapter
            }


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner4,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner2.adapter = adapter
            }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner7,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner3.adapter = adapter
            }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner8,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner4.adapter = adapter
            }


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner9,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner5.adapter = adapter
            }
    }

    private fun onClick() {
        binding.nextButton.setOnClickListener {
            findNavController().navigate(ObserverFragmentDirections.actionObserverFragmentToAddNewEvent())
        }

        binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cancel.setOnClickListener {
            MyDialog(requireContext()).cancel {
                binding.cancel.setOnClickListener {
                    findNavController().navigate(ObserverFragmentDirections.actionObserverFragmentToNewSurveyFragment())
                }
            }
        }
    }

}