package kr.edcan.ssf2022.model.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kr.edcan.ssf2022.model.data.Diary
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.util.Collection
import kr.edcan.ssf2022.util.Result
import java.text.SimpleDateFormat

class DiaryRepositoryImpl : DiaryRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun writeDiary(diaryDate: Diary, userData: User): Int {
        var result = Result.FAILED
        val dateFormat : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        db.collection(Collection.diary).document(userData.email)
            .collection(Collection.diary).document(dateFormat.format(diaryDate.date)).set(diaryDate)
            .addOnSuccessListener {
                result = Result.SUCCESS
            }
            .await()

        return result
    }

    override suspend fun getDiaryAll(userData: User): List<Diary>? {
        var result : MutableList<Diary>? = mutableListOf<Diary>()

        db.collection(Collection.diary).document(userData.email)
            .collection(Collection.diary)
            .get()
            .addOnSuccessListener {
                for(doc in it){
                   result!!.add(doc.toObject(Diary::class.java))
                    Log.d("diaryData", doc.toObject(Diary::class.java).toString())
                }
            }
            .addOnFailureListener {
                result = null
            }
            .await()

        return result
    }

}