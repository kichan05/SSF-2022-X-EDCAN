package kr.edcan.ssf2022.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.edcan.ssf2022.model.data.Diary
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.model.remote.AuthRepositoryImpl
import kr.edcan.ssf2022.model.remote.DiaryRepository
import kr.edcan.ssf2022.model.remote.DiaryRepositoryImpl
import kr.edcan.ssf2022.util.Result
import kr.edcan.ssf2022.util.State
import java.util.*

class MainViewModel : ViewModel() {
    private val diaryRepository = DiaryRepositoryImpl()

    val userData: MutableLiveData<User> = MutableLiveData()
    val diaryList : MutableLiveData<List<Diary>> = MutableLiveData(mutableListOf())

    val state : MutableLiveData<Int> = MutableLiveData(State.SUCCESS)

    fun getDiaryList() {
        state.value = State.LOADING

        viewModelScope.launch {
            val result : List<Diary>? = diaryRepository.getDiaryAll(userData.value!!)

            if(result == null) {
                state.value = State.FAIL
            }
            else {
                diaryList.value = result
                state.value = State.SUCCESS
            }
        }
    }
}