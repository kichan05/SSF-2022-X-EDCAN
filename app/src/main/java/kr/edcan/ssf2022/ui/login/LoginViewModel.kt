package kr.edcan.ssf2022.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.model.remote.AuthRepositoryImpl
import kr.edcan.ssf2022.util.State

class LoginViewModel : ViewModel() {
    private val auth = AuthRepositoryImpl()

    val state = MutableLiveData<Int>()
    val inputEmail = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()

    var userData: MutableLiveData<User> = MutableLiveData()

    val errorMessage = MutableLiveData<String?>(null)

    fun login() {
        state.value = State.LOADING
        viewModelScope.launch {
            val result = auth.login(email = inputEmail.value!!, password = inputPassword.value!!)
            userData.value = result

            Log.d("loginLog", result.toString())

            state.value = if (result != null) {
                //로그인에 성공 한 경우
                State.SUCCESS
            } else {
                Log.d("loginLog", "실패")
                //로그인에 실패한 경우
                State.FAIL
            }
        }
    }

    fun autoLogin() {
        /*
        * 자동로그인을 징행하는 함수
        * 이미 로그인이 됐는지 구한 뒤
        * 로그인이 됐다면 해당 유저 정보를 반환한다.
        * 로그인이 되지 않았다면 null을 반환
        * */

        if (auth.isAlreadyLogin) {  // 사용자가 이미 로그인을 했다면
            viewModelScope.launch {
                Log.d(
                    "register",
                    auth.currentUser!!.email.toString()
                )
                val user = auth.getUserDataByEmail(email = auth.currentUser!!.email.toString())!!
                userData.value = user
            }
        }
    }
}