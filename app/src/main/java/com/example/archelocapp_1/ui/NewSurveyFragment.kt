package com.example.archelocapp_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.R
import com.example.archelocapp_1.databinding.ActivityArrowBtnBinding
import com.example.archelocapp_1.utils.Preference

class NewSurveyFragment : Fragment(){

    private lateinit var binding: ActivityArrowBtnBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityArrowBtnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        binding.startNewSurvey.setOnClickListener {
            findNavController().navigate(NewSurveyFragmentDirections.actionNewSurveyFragmentToStartSurveyFragment())

        }

        binding.logout.setOnClickListener {
            Preference(requireContext()).clear()
        }
    }






}