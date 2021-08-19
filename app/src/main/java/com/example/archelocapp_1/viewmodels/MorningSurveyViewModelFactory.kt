package com.example.archelocapp_1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archelocapp_1.repository.MorningSurveyRepository

class MorningSurveyViewModelFactory(private val repository: MorningSurveyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MorningSurveyViewModel::class.java)) {
            return MorningSurveyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
