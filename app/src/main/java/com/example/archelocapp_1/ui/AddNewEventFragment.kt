package com.example.archelocapp_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.databinding.FragmentNewEventBinding
import com.example.archelocapp_1.utils.MyDialog

class AddNewEventFragment : Fragment() {

    private lateinit var binding: FragmentNewEventBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.addNewEvent.setOnClickListener {
            findNavController().navigate(AddNewEventFragmentDirections.actionAddNewEventToChooseEventFragment())
        }

        binding.cancel.setOnClickListener {
            MyDialog(requireContext()).cancel {
                binding.cancel.setOnClickListener {
                    findNavController().navigate(AddNewEventFragmentDirections.actionAddNewEventToNewSurveyFragment())
                }
            }
        }
    }

}