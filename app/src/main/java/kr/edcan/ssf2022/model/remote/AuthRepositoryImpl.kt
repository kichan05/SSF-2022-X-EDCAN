package kr.edcan.ssf2022.model.remote

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.util.Result
import kr.edcan.ssf2022.util.Collection
import kr.edcan.ssf2022.util.Temp

class AuthRepositoryImpl : AuthRepository {
//    val auth_: FirebaseAuth = FirebaseAuth.getInstance()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()

    override suspend fun register(userData: User, password: String, profileImage: Uri?): Int {
        /*
        * 회원가입을 진행하는 함수
        * firebase의 유저를 만들고, 유저 정보를 저장하고, 프로필 이미지를 업로드 한다.
        * createUser, saveUserData, uploadProfileImage 함수 실행
        * */

        val createUserResult = createUser(userData, password)
        if (createUserResult == Result.FAILED)
            return Result.FAILED

        Log.d("register", "생성 완")

        val profileUploadResult = if (profileImage != null) {
            uploadProfileImage(userData, profileImage) ?: return Result.FAILED
        } else {
            Temp.basics_profile_image
        }

        Log.d("register", profileUploadResult)

        val _userData = with(userData) {
            User(
                email = email,
                name = name,
                message = message,
                profileImage = profileUploadResult
            )
        }

        val saveUserData = saveUserData(_userData, profileUploadResult)
        if (saveUserData == Result.FAILED) {
            Log.d("register", "저장 실패")
            return Result.FAILED
        }

        Log.d("register", "저장 완")


        return Result.SUCCESS
    }

    override suspend fun createUser(userData: User, password: String): Int {
        /*
        * Firebase Auth에 계정을 만드는 함수
        * */
        var result = Result.FAILED
        auth.createUserWithEmailAndPassword(userData.email, password)
            .addOnSuccessListener {
                result = Result.SUCCESS
            }
            .await()

        return result
    }

    override suspend fun saveUserData(userData: User, profileImage: String): Int {
        /*
        * Firebase Forestore에 계정 정보를 저장하는 함수 
        * */
        var result = Result.FAILED
        db.collection(Collection.auth).document(userData.email)
            .set(userData)
            .addOnSuccessListener {
                result = Result.SUCCESS
            }
            .addOnFailureListener {
                Log.e("register", it.message.toString())
            }
            .await()

        return result
    }

    override suspend fun uploadProfileImage(userData: User, profileImage: Uri): String? {
        var result: String? = null
        val imageRef = storage.reference.child("user/${userData.email}.png")

        imageRef.putFile(profileImage)
            .continueWithTask {
                if (!it.isSuccessful) {
                    Log.d("register", it.exception!!.message.toString())
                }
                imageRef.downloadUrl
            }
            .addOnCompleteListener {
                result = it.result.toString()
            }
            .addOnFailureListener {
                Log.e("register", it.message.toString())
            }
            .await()

        return result
    }

    override suspend fun login(email: String, password: String): User? {
        /*
        * 로그인을 진행하는 함수
        * 사용자가 입력한 이메일에 해당하는 사용자 정보를 가져온다.
        * 사용자 정보가 없다면 로그인을 진행하지 않는다
        * 사용자 정보가 있으면 로그인을 진행한다.
        * */


        var result: Boolean = false

        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    result = true
                }
                .addOnFailureListener {
                    Log.e("[login error]", "${it.message}")
                    result = false
                }
                .await()
        }
        catch (e : FirebaseAuthInvalidUserException){
            return null
        }
        catch (e : FirebaseAuthInvalidCredentialsException){
            Log.e("[login error]", "로그인 에러 발생함")
            return null
        }

        return getUserDataByEmail(email)
    }

    override suspend fun getUserDataByEmail(email: String): User? {
        /* 이메일을 이용해서 사용자의 정보를 가져오는 함수 */
        var result: User? = null

        db.collection(Collection.auth).document(email).get()
            .addOnSuccessListener {
                result = it.toObject(User::class.java)
            }
            .await()

        return result
    }

    val isAlreadyLogin: Boolean
        get() = auth.currentUser != null

    val currentUser
        get() = auth.currentUser
}