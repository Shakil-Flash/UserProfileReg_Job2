package com.flash.userprofilereg_job2.view.singleprofile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.flash.userprofilereg_job2.data.UserProfile
import com.flash.userprofilereg_job2.databinding.ActivitySingleProfileBinding
import com.flash.userprofilereg_job2.databinding.DialogBinding
import com.flash.userprofilereg_job2.view.addprofile.AddProfileActivity
import com.flash.userprofilereg_job2.viewmodel.UserProfileViewModel

class SingleProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleProfileBinding
    private lateinit var userViewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySingleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", -1)
        val name = intent.getStringExtra("name") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val dob = intent.getStringExtra("dob") ?: ""
        val address = intent.getStringExtra("address") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""

        binding.apply {
            tvName.text = name
            tvEmail.text = email
            tvDate.text = dob
            tvAddress.text = address
            tvPhone.text = phone

            btndlt.setOnClickListener {
                showDeleteDialog(id, name, email, dob, address, phone)
            }



            btnEdit.setOnClickListener {
                val intent = Intent(this@SingleProfileActivity, AddProfileActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", name)
                intent.putExtra("email", email)
                intent.putExtra("dob", dob)
                intent.putExtra("address", address)
                intent.putExtra("phone", phone)
                startActivity(intent)
                finish()
            }

            back.setOnClickListener {
                finish()
            }

        }
    }
    private fun showDeleteDialog(id: Int, name: String, email: String, dob: String, address: String, phone: String) {
        val dialogBinding = DialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogBinding.btnConfirm.setOnClickListener {
            val userToDelete = UserProfile(id, name, email, dob, address, phone)

            userViewModel.deleteUserProV(userToDelete)

            dialog.dismiss()
            finish()
        }

        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}