package kr.edcan.ssf2022.ui.register

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.sql.Struct

class Register3ViewModel : ViewModel() {
    val profileImage : MutableLiveData<Uri> = MutableLiveData()
    val message : MutableLiveData<String> = MutableLiveData("")

    val isNextEnable : MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkNext(){
        isNextEnable.value = !message.value.isNullOrEmpty()
    }
}

/*
찬이형 사랑합니다😚
 */