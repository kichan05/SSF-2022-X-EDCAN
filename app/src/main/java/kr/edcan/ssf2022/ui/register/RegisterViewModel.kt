package kr.edcan.ssf2022.ui.register

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.model.remote.AuthRepositoryImpl
import kr.edcan.ssf2022.util.Result
import kr.edcan.ssf2022.util.State

class RegisterViewModel : ViewModel() {
    val auth = AuthRepositoryImpl()

    val state: MutableLiveData<Int> = MutableLiveData()

    val userData: MutableLiveData<User> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData("")
    val profile: MutableLiveData<Uri?> = MutableLiveData()

    fun register() {
        state.value = State.LOADING

        viewModelScope.launch {
            Log.d("register", "시작")
            val result = auth.register(userData.value!!, password.value!!, profile.value)
            Log.d("register", "끝")

            when (result){
                Result.SUCCESS -> {
                    state.value = State.SUCCESS
                }
                else -> {
                    state.value = State.FAIL
                }
            }
        }    
    }
}