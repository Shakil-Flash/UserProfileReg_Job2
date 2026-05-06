package com.flash.userprofilereg_job2.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "User_Profile")
data class UserProfile(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var email: String,
    var dob: String,
    var Address: String,
    var number: String,
)
