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
import java.time.LocalDate
import java.util.*

class Main1ViewModel : ViewModel() {
    val selectedData = MutableLiveData<Date>(Date())
    val isSelectedDataDiary = MutableLiveData<Boolean>()
    val selectedDiary = MutableLiveData<Diary>()
}