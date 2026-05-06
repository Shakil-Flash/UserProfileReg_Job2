package com.flash.userprofilereg_job2.view.profilelist

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.flash.userprofilereg_job2.databinding.ActivityProfileListBinding
import com.flash.userprofilereg_job2.databinding.DialogBinding
import com.flash.userprofilereg_job2.view.addprofile.AddProfileActivity
import com.flash.userprofilereg_job2.view.singleprofile.SingleProfileActivity
import com.flash.userprofilereg_job2.viewmodel.UserProfileViewModel

class ProfileListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileListBinding

    private lateinit var userViewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdduser.setOnClickListener {
            startActivity(Intent(this, AddProfileActivity::class.java))
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel = ViewModelProvider(this)[UserProfileViewModel::class.java]

        userViewModel.livedata.observe(this) {it ->
            binding.tvEmployeeCount.text = "${it.size}"

            val adapter = ProfileAdapter(
                it,
                onEdit = {user ->
                val intent = Intent(this, SingleProfileActivity::class.java)
                    intent.putExtra("id", user.id)
                    intent.putExtra("user", user.name)
                    intent.putExtra("email", user.email)
                    intent.putExtra("address", user.Address)
                    intent.putExtra("dob", user.dob)
                    intent.putExtra("phone", user.number)
                    startActivity(intent)
            },
                onDelete = {data ->
                    val dialogBinding = DialogBinding.inflate(layoutInflater)
                    val dialog = AlertDialog.Builder(this)
                        .setView(dialogBinding.root)
                        .create()

                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                    dialogBinding.btnConfirm.setOnClickListener {
                        userViewModel.deleteUserProV(data)
                        dialog.dismiss()

                    }
                    dialogBinding.btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.show()
                },
                onClick = { user ->
                    val intent = Intent(this, SingleProfileActivity::class.java)
                    intent.putExtra("id", user.id)
                    intent.putExtra("name", user.name)
                    intent.putExtra("email", user.email)
                    intent.putExtra("address", user.Address)
                    intent.putExtra("dob", user.dob)
                    intent.putExtra("phone", user.number)
                    startActivity(intent)
                })

            binding.recyclerView.adapter = adapter
        }

    }

    override fun onResume() {
        super.onResume()
        userViewModel.loadUserProV()

    }
}