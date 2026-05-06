package com.flash.userprofilereg_job2.repo

import com.flash.userprofilereg_job2.data.UserProfile
import com.flash.userprofilereg_job2.data.UserProfileDao

class UserProfileRepository(private val userProDao: UserProfileDao) {

    fun getAllUserPro(): List<UserProfile> {
        return userProDao.getAllUserProfiles()
    }

    fun insertUserPro(userPro: UserProfile) {
        userProDao.insertUserProfile(userPro)
    }

    fun updateUserPro(userPro: UserProfile) {
        userProDao.updateUserProfile(userPro)
    }

    fun deleteUserPro(userPro: UserProfile) {
        userProDao.deleteUserProfile(userPro)
    }
}