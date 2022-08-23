package kr.edcan.ssf2022.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.model.data.User

class TestActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        db = FirebaseFirestore.getInstance()

        val test = findViewById<Button>(R.id.btn_test_test)
        test.setOnClickListener {
            Log.d("firebaseTest", "테스크 클릭")
            val user = User(
                email = "ew@naver.com",
                name = "제발 돼라",
                message = "진짜 제발",
                profileImage = "ㅁㄴㅇㅁㄴ"
            )

            db.collection("test").add(user)
                .addOnSuccessListener {
                    Log.d("firebaseTest", "테스트 성공 ${it.id}")
                }
                .addOnFailureListener {
                    Log.d("firebaseTest", "테스트 실패")
                }
        }

        val get = findViewById<Button>(R.id.btn_test_get)
        get.setOnClickListener {
            Log.d("firebaseTest", "가져오기 클릭")
            db.collection("test").get()
                .addOnSuccessListener {
                    for (doc in it)
                        Log.d("firebaseTest", "가져오기 성공 ${doc.data}")
                }
                .addOnFailureListener {
                    Log.d("firebaseTest", "가져오기 실패")
                }
        }


    }
}