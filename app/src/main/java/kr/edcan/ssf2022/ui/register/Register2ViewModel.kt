package kr.edcan.ssf2022.ui.register

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.sql.Struct

class Register2ViewModel : ViewModel() {
    val password : MutableLiveData<String> = MutableLiveData()
    val passwordRe : MutableLiveData<String> = MutableLiveData()

    val isNextEnable : MutableLiveData<Boolean> = MutableLiveData(false)

    val errorMessage = MutableLiveData<String?>(null)

    fun checkNext(){
        isNextEnable.value = !(password.value.isNullOrEmpty() || passwordRe.value.isNullOrEmpty())
    }
}

/*
찬이형 사랑합니다😚
 */