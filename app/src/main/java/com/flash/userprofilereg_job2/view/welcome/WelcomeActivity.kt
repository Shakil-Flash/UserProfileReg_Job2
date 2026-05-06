package com.flash.userprofilereg_job2.view.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.flash.userprofilereg_job2.databinding.ActivityWelcomeBinding
import com.flash.userprofilereg_job2.view.profilelist.ProfileListActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profileButton.setOnClickListener {
            val intent = Intent(this, ProfileListActivity::class.java)
            startActivity(intent)
        }
    }
}