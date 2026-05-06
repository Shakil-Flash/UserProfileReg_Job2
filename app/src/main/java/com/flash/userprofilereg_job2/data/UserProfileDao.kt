package com.flash.userprofilereg_job2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserProfileDao {

    @Insert
    fun insertUserProfile(userProfile: UserProfile)

    @Update
    fun updateUserProfile(userProfile: UserProfile)

    @Delete
    fun deleteUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM user_profile ORDER BY id DESC")
    fun getAllUserProfiles(): List<UserProfile>
}