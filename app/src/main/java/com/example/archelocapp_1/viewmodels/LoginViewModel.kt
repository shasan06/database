package com.example.archelocapp_1.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archelocapp_1.Models.*
import com.example.archelocapp_1.repository.Repository
import com.example.archelocapp_1.source.api.ServiceBuilder
import com.example.archelocapp_1.utils.AuthListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class LoginViewModel : ViewModel() {
    private val TAG = "LoginViewModel"

    var token = ""
    var authChecking = "not_complete"
    private var viewModelJob = Job()
    //A coroutine scope is defined that is used for API call
    val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Default
    )

    //init block for creating loginview model
    init {
        Log.i("LoginViewModel", "LoginViewModel created!")
    }

    fun loginCredentials(username: String, password: String){
        //creating a REST API request to validate the user login credentials
        authChecking = "in_progress"
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", username)
            .addFormDataPart("password", password)
            .build()

        //launching a REST request on different thread so that the app does not break
        coroutineScope.launch {
            var getPropertiesDeferred = LoginApi.retrofitService.authenticateUser(requestBody)
            try {
                var listResult = getPropertiesDeferred.await()
                token = listResult.key
                authChecking = "complete"
                Log.i("token", "token:" + token)
            }catch (e: Exception) {
                var response = "Failure: ${e.message}"
                authChecking = "incomplete"
                Log.e("Auth Checking" , response)
            }



        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

//old code below
//    private var repository = Repository()
//    private var isLogin = MutableLiveData<Boolean>(false)


//    fun get() = isLogin
//
//    fun login(userName: String, password: String, listener: AuthListener<Boolean>) {
//        viewModelScope.launch {
//            ServiceBuilder.KEY = ""
//            val data = repository.login(userName, password)
//            if (data.isSuccessful) {
//                listener.onSuccess(true)
//                val login = data.body() as Login
//                ServiceBuilder.KEY = "Token ${login.key}"
//            } else {
//                listener.onSuccess(false)
//            }
//
//        }
//    }

//old code with

}

