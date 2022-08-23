package kr.edcan.ssf2022.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Diary(
    val date: Date = Date(),
    val weather : Int = 0,
    val emotion : Int = 0,
    val content : String = "",
) : Parcelable
