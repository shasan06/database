package com.example.archelocapp_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.databinding.FragmentChooseEventBinding
import com.example.archelocapp_1.utils.MyDialog

class ChooseEventFragment : Fragment() {

    private lateinit var binding: FragmentChooseEventBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.adultEmergence.setOnClickListener {
            findNavController().navigate(ChooseEventFragmentDirections.actionChooseEventFragmentToAdultEmergencyFragment())
        }


        binding.cancel.setOnClickListener {
            MyDialog(requireContext()).cancel {
                binding.cancel.setOnClickListener {
                    findNavController().navigate(ChooseEventFragmentDirections.actionChooseEventFragmentToNewSurveyFragment())
                }
            }

        }
    }

}