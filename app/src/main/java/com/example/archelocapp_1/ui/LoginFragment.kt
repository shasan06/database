package com.example.archelocapp_1.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.Models.Login

import com.example.archelocapp_1.R
import com.example.archelocapp_1.databinding.ActivityMainBinding
import com.example.archelocapp_1.utils.AuthListener
import com.example.archelocapp_1.viewmodels.LoginViewModel
import java.util.EnumSet.of
import java.util.List.of
import java.util.Map.of
import java.util.Optional.of
import java.util.OptionalDouble.of
import java.util.OptionalInt.of
import java.util.Set.of


// Fragment for the screen 1 that is the login in screen

class LoginFragment  : Fragment() {

    private val TAG = "LoginFragment"
    //lateinit makes a promise to the compiler to initialise the variable later
    //private lateinit var binding:  ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel

    fun authentificate(name: String, password: String, navigationControl: NavController, binding: ActivityMainBinding){
        loginViewModel.loginCredentials(name, password)
        while (loginViewModel.authChecking == "not_complete" || loginViewModel.authChecking == "in_progress") {
        }

        if (loginViewModel.authChecking == "complete") {

            Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
            //findNavController().navigate(LoginFragmentDirections.actionLoginFragmentPopIncludingNewSurveyFragment())
            //if the user authentication is correct it displays a toast with a message login success in Screen2

            // Password recieved  authenticated token to next fragment
            val action = LoginFragmentDirections.actionLoginFragmentPopIncludingNewSurveyFragment(loginViewModel.token)//Login_FragmentDirections.surveyOptions()
            navigationControl.navigate(action)
            loginViewModel.authChecking = "not_complete"

        } else {
            binding.UsernameBtn.setTextColor(Color.rgb(255, 0, 0))
            binding.PasswordBtn.setTextColor(Color.rgb(255, 0, 0))
            loginViewModel.authChecking = "not_complete"
            //if the user authentication is not complete/incorrect the text of username and password will turn red
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: ActivityMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false)

        val navigationControl = findNavController()
        Log.i("LoginFragment", "Calling ViewModelProviders")
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.image.setOnClickListener {

            val username = "shasan06";
            val password = "^dusk|FULL|HIGH^";
            authentificate(username, password, navigationControl, binding)
        }


        binding.arrowBtn.setOnClickListener {

            val username = binding.UsernameBtn.text.toString();
            val password = binding.PasswordBtn.text.toString();




            //User Authentication based on entering the login credentials (loginCredentials is a function in LoginviewModel)
            authentificate(username,password, navigationControl, binding)

        }
        return binding.root
    }


}




//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//
//        init()
//
//    }
//
//    private fun init() {
//        binding.arrowBtn.setOnClickListener{
//
//            with(loginViewModel){
//               var userName = binding.UsernameBtn.text.toString()
//                var password = binding.PasswordBtn.text.toString()
//                if (userName.isEmpty()) userName = "shasan06"
//                if (password.isEmpty()) password = "^dusk|FULL|HIGH^"
//                login(userName, password , object : AuthListener<Boolean> {
//                    override fun onSuccess(v: Boolean) {
//                        if (v) {
//                            Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
//                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentPopIncludingNewSurveyFragment())
//                        }
//                    }
//                })
//
//
//
////                loginViewModel.addNest("BLABLA")
//
//
//
//            }
//        }
//
//
//
//
//
//    }
//}

//to authenticate
// Authenticate User based on credentials entered
//viewModel.checkUserCredentials(username_login.toString(),password_login.toString())
//while( viewModel.authCheck == "not_complete" || viewModel.authCheck == "in_progress") { }
//
//if (viewModel.authCheck == "complete") {
//
//    // Pass recieved  authenticated token to next fragment
//    val action = Login_FragmentDirections.surveyOptions()
//    action.token = viewModel.token
//    navController.navigate(action)
//    viewModel.authCheck = "not_complete"
//
//} else {
//    binding.usernameText.setTextColor(Color.rgb(255, 0, 0))
//    binding.passwordText.setTextColor(Color.rgb(255, 0, 0))
//    viewModel.authCheck = "not_complete"
//
//}
//
//}
//return binding.root
//}