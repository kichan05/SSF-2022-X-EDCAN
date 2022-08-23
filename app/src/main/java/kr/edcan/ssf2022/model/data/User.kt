package kr.edcan.ssf2022.model.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email : String = "",
    val name : String = "",
    val message : String = "",
    val profileImage : String = "",
    var diaryCount : Int = 0,
) : Parcelable