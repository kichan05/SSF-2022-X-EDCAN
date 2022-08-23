package kr.edcan.ssf2022.ui.write

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.model.data.Diary
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.model.remote.DiaryRepositoryImpl
import kr.edcan.ssf2022.util.Emotion
import kr.edcan.ssf2022.util.Result
import kr.edcan.ssf2022.util.State
import kr.edcan.ssf2022.util.Weather
import java.util.*

class WriteViewModel : ViewModel() {
    private val diaryRepository = DiaryRepositoryImpl()

    val userData = MutableLiveData<User>()

    val weather = MutableLiveData<Int>(R.id.rb_write_sun)
    val emotion = MutableLiveData<Int>(R.id.rb_gb_happy)
    val content = MutableLiveData<String>()

    val state = MutableLiveData<Int>()

    fun writeDiary() {
        
        val selectedWeather : Int = when(weather.value){
            R.id.rb_write_sun -> Weather.sun
            R.id.rb_write_cloud -> Weather.cloud
            R.id.rb_write_rain -> Weather.rain
            R.id.rb_write_sunRain -> Weather.sunRain
            R.id.rb_write_wind -> Weather.wind
            else -> Weather.snow
        }

        val selectedEmotion = when(emotion.value){
            R.id.rb_gb_happy -> Emotion.happy
            R.id.rb_gb_fun -> Emotion.fun_
            R.id.rb_gb_wow -> Emotion.wow
            R.id.rb_gb_normal -> Emotion.normal
            R.id.rb_gb_sad -> Emotion.sad
            else -> Emotion.angry
        }

        val diaryData = Diary(
            date = Date(),
            weather = selectedWeather,
            emotion = selectedEmotion,
            content = content.value!!,
        )

        state.value = State.LOADING

        viewModelScope.launch {
            val writeDiaryResult = diaryRepository.writeDiary(diaryData, userData.value!!)

            when (writeDiaryResult) {
                Result.SUCCESS -> {
                    state.value = State.SUCCESS
                }
                Result.FAILED ->{
                    state.value = State.FAIL
                }
            }
        }
    }
}