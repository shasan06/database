package com.example.archelocapp_1.ui

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.databinding.FragmentAdultEmergenceBinding
import com.example.archelocapp_1.utils.MyDialog
import com.example.archelocapp_1.utils.Preference
import com.example.archelocapp_1.viewmodels.AdultEmergencyViewModel

class AdultEmergencyFragment : Fragment() {

    private lateinit var binding: FragmentAdultEmergenceBinding
    private val adultEmergencyViewModel: AdultEmergencyViewModel by viewModels()
    private lateinit var preference: Preference
    var saveContact = ""
    var tempData = ""
    var attemptCount = 0
    var imageCount = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdultEmergenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preference = Preference(requireContext())



        init()
        onClick()

    }

    private fun init() {

        val emergencyNo = adultEmergencyViewModel.getEmergencyNo(preference)
        binding.emergencyNo.text = emergencyNo.toString()
    }

    private fun onClick() {
        binding.nextButton.setOnClickListener {
            findNavController().navigate(AdultEmergencyFragmentDirections.actionAdultEmergencyFragmentToSuspectedNestFragment())
        }
        binding.cancel.setOnClickListener {
            MyDialog(requireContext()).cancel {
                findNavController().navigate(AdultEmergencyFragmentDirections.actionAdultEmergencyFragmentToAddNewEvent())
            }
        }

        binding.previousButton.setOnClickListener {
           findNavController().popBackStack()
        }

        binding.btNA.setOnClickListener { addData(binding.btNA.text.toString()) }
        binding.btBP.setOnClickListener { addData(binding.btBP.text.toString()) }
        binding.btAEC.setOnClickListener { addData(binding.btAEC.text.toString()) }
        binding.btS.setOnClickListener { addData(binding.btS.text.toString()) }
        binding.btSBP.setOnClickListener { addData(binding.btSBP.text.toString()) }
        binding.btSAEC.setOnClickListener { addData(binding.btSAEC.text.toString()) }
        binding.btNEST.setOnClickListener { addData(binding.btNEST.text.toString()) }
        binding.btSNEST.setOnClickListener { addData(binding.btSNEST.text.toString()) }

        binding.add.setOnClickListener {
            checkData(tempData)
        }
        binding.clear.setOnClickListener {
            saveContact = ""
            tempData = ""
            binding.textData.text = ""
            attemptCount = 0
            binding.attemptNo.text = "$attemptCount"
        }
        binding.nextButton.setOnClickListener {
            adultEmergencyViewModel.saveData(
                binding.textData.text.toString(),
                preference
            )
            findNavController().navigate(AdultEmergencyFragmentDirections.actionAdultEmergencyFragmentToSuspectedNestFragment())
        }

        binding.capture.setOnClickListener { selectImageInAlbum() }
    }

    private fun addData(data: String) {
        tempData = data
    }

    private fun selectImageInAlbum() {
        try {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        } catch (e: Exception) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            binding.photoRecord.visibility = View.GONE
            val uri = data?.data
            imageCount++
            if (imageCount == 1)
                binding.imageView1.setImageURI(uri)
            else if (imageCount == 2) {
                binding.imageView2.setImageURI(uri)
            } else if (imageCount == 3) {
                binding.imageView3.setImageURI(uri)
            }
        } catch (e: Exception) {
        }

    }

    private fun checkData(data: String) {
        val checkData = adultEmergencyViewModel.checkData(data, preference)

        if (attemptCount < 3) {
            if (checkData && !saveContact.contains(data)) {
                attemptCount++
                binding.attemptNo.text = "$attemptCount"
                saveContact = if (saveContact.isEmpty())
                    data
                else
                    "$saveContact, $data"

                binding.textData.text = saveContact

            } else
                Toast.makeText(requireContext(), "Already Present", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(requireContext(), "No more attempts", Toast.LENGTH_SHORT).show()
        }

    }

}