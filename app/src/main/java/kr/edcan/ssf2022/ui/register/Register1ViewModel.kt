package kr.edcan.ssf2022.ui.register

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.sql.Struct

class Register1ViewModel() : ViewModel() {
    val name : MutableLiveData<String> = MutableLiveData()
    val email : MutableLiveData<String> = MutableLiveData()
    val isNextEnable : MutableLiveData<Boolean> = MutableLiveData(false)

    val errorMessage = MutableLiveData<String?>(null)

    fun checkNext(){
        isNextEnable.value = !(name.value.isNullOrEmpty() || email.value.isNullOrEmpty())
    }
}

/*
찬이형 사랑합니다😚
 */