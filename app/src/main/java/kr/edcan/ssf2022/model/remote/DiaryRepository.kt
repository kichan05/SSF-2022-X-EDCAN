package kr.edcan.ssf2022.model.remote

import kr.edcan.ssf2022.model.data.Diary
import kr.edcan.ssf2022.model.data.User

interface DiaryRepository {
    suspend fun writeDiary(diaryDate : Diary, userData : User) : Int
    suspend fun getDiaryAll(userData : User) : List<Diary>?
}