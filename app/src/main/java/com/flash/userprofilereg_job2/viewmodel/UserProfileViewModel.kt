package com.flash.userprofilereg_job2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.flash.userprofilereg_job2.data.UserProfile
import com.flash.userprofilereg_job2.data.UserProfileDatabase
import com.flash.userprofilereg_job2.repo.UserProfileRepository

class UserProfileViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserProfileRepository

    val livedata = MutableLiveData<List<UserProfile>>()

    init {
        val dao = UserProfileDatabase.getDatabase(application).userDao()
        repository = UserProfileRepository(dao)
        loadUserProV()
    }

    fun insertUserProV(userPro: UserProfile) {
        repository.insertUserPro(userPro)
        loadUserProV()
    }

    fun updateUserProV(userPro: UserProfile) {
        repository.updateUserPro(userPro)
        loadUserProV()
    }

    fun deleteUserProV(userPro: UserProfile) {
        repository.deleteUserPro(userPro)
        loadUserProV()
    }

    fun loadUserProV() {
        val data = repository.getAllUserPro()
        livedata.value = data
    }


}