package com.flash.userprofilereg_job2.view.addprofile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.flash.userprofilereg_job2.R
import com.flash.userprofilereg_job2.data.UserProfile
import com.flash.userprofilereg_job2.databinding.ActivityAddProfileBinding
import com.flash.userprofilereg_job2.viewmodel.UserProfileViewModel

class AddProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProfileBinding

    private lateinit var userViewModel: UserProfileViewModel
    private var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            finish()
        }

        userViewModel = ViewModelProvider(this)[UserProfileViewModel::class.java]
        noteId = intent.getIntExtra("id", -1)

        if (noteId != -1) {
            val name = intent.getStringExtra("name")
            val email = intent.getStringExtra("email")
            val dob = intent.getStringExtra("dob")
            val address = intent.getStringExtra("address")
            val phone = intent.getStringExtra("phone")

            binding.apply {
                etName.setText(name)
                etEmail.setText(email)
                etDob.setText(dob)
                etAddress.setText(address)
                etPhone.setText(phone)
            }

        }
        binding.btnSave.setOnClickListener {

            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val dob = binding.etDob.text.toString().trim()
            val address = binding.etAddress.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                binding.apply {
                    etName.error = "Name Required"
                    etEmail.error = "Email Required"
                    etDob.error = "Date Of Birth Required"
                    etAddress.error = "Address Required"
                    etPhone.error = "Phone Number Required"
                    return@setOnClickListener


                }

            } else {
                binding.apply {
                    etName.error = null
                    etEmail.error = null
                    etDob.error = null
                    etAddress.error = null
                    etPhone.error = null

                }
            }
            if (noteId == -1) {
                val user = UserProfile(
                    name = name, email = email, dob = dob, Address = address, number = phone
                )
                userViewModel.insertUserProV(user)

            } else {
                val user = UserProfile(
                    id = noteId,
                    name = name,
                    email = email,
                    dob = dob,
                    Address = address,
                    number = phone
                )
                userViewModel.updateUserProV(user)


            }
            Toast.makeText(
                this, "Data save successfully", Toast.LENGTH_SHORT
            ).show()
            finish()

        }

    }
}