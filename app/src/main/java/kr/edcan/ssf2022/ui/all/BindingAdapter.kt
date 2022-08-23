package kr.edcan.ssf2022.ui.all

import android.net.Uri
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI.getApplicationContext
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.util.Emotion
import kr.edcan.ssf2022.util.Weather
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("bindVisibility")
fun bindVisibility(view : View, visibility : Boolean){
    view.visibility = if(visibility) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("input_enabled")
fun bindEnabled(v : Button, condition : Boolean) {
    v.isEnabled = condition
}

@BindingAdapter("uri")
fun bindUri(v : ImageView, image : Uri){
    v.setImageURI(image)
}

@BindingAdapter("bindRbCurrentSelect")
fun bindRadioButtonChoice(radioButton: RadioButton, selectedId : Int) {
    radioButton.isChecked = radioButton.id == selectedId
}

@BindingAdapter("bindSrc")
fun bindSrc(imageView: ImageView, url : String){
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter("bindDateFormat")
fun bindDateFormat(textView: TextView, date : Date){
    val simpleDateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
    val strNowDate = simpleDateFormat.format(date)

    textView.text = strNowDate
}

@BindingAdapter("bindToolBarDateFormat")
fun bindToolBarDateFormat(toolBar : androidx.appcompat.widget.Toolbar, date : Date){
    toolBar.title = "${date.year + 1900}년 ${date.month + 1}월 ${date.date}일 일기"
}

@BindingAdapter("bindWeatherNormalIconId")
fun bindWeatherNormalIconId(imageview: ImageView, weatherId: Int) {
    val iconId = when(weatherId){
        Weather.sun -> R.drawable.ic_sun
        Weather.cloud -> R.drawable.ic_cloud
        Weather.rain -> R.drawable.ic_rain
        Weather.sunRain -> R.drawable.ic_sun_rain
        Weather.wind -> R.drawable.ic_wind
        Weather.snow -> R.drawable.ic_snow
        else -> R.drawable.ic_sun
    }
    imageview.setImageResource(iconId)
}

@BindingAdapter("bindEmotionNormalAcIconId")
fun bindEmotionNormalAcIconId(imageview: ImageView, emotionId : Int){
    val iconId = when(emotionId){
        Emotion.happy -> R.drawable.ic_happy
        Emotion.fun_ -> R.drawable.ic_fun
        Emotion.wow -> R.drawable.ic_wow
        Emotion.normal -> R.drawable.ic_nomal
        Emotion.sad -> R.drawable.ic_sad
        Emotion.angry -> R.drawable.ic_angry
        else -> R.drawable.ic_happy
    }

    imageview.setImageResource(iconId)
}

@BindingAdapter("bindWeatherAcIconId")
fun bindWeatherAcIconId(imageview: ImageView, weatherId: Int) {
    val iconId = when(weatherId){
        Weather.sun -> R.drawable.ic_sun_ac
        Weather.cloud -> R.drawable.ic_cloud_ac
        Weather.rain -> R.drawable.ic_rain_ac
        Weather.sunRain -> R.drawable.ic_sun_rain_ac
        Weather.wind -> R.drawable.ic_wind_ac
        Weather.snow -> R.drawable.ic_snow_ac
        else -> R.drawable.ic_sun_ac
    }
    imageview.setImageResource(iconId)
}

@BindingAdapter("bindEmotionAcIconId")
fun bindEmotionAcIconId(imageview: ImageView, emotionId : Int){
    val iconId = when(emotionId){
        Emotion.happy -> R.drawable.ic_happy_ac
        Emotion.fun_ -> R.drawable.ic_fun_ac
        Emotion.wow -> R.drawable.ic_wow_ac
        Emotion.normal -> R.drawable.ic_nomal_ac
        Emotion.sad -> R.drawable.ic_sad_ac
        Emotion.angry -> R.drawable.ic_angry_ac
        else -> R.drawable.ic_happy_ac
    }

    imageview.setImageResource(iconId)
}

@BindingAdapter("bindInputErrorMessage")
fun bindInputErrorMessage(textView : TextView, errorMessage : String?){
    if(errorMessage == null){
        textView.visibility = View.INVISIBLE
    }
    else {
        val animation: Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_error_message)
        textView.startAnimation(animation)

        textView.visibility = View.VISIBLE

        textView.text = errorMessage
    }
}