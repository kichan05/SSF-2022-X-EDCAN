package kr.edcan.ssf2022.model.remote

import android.net.Uri
import kr.edcan.ssf2022.model.data.User
import java.net.URL

interface AuthRepository {
    suspend fun register(userData : User, password : String, profileImage: Uri?) : Int
    suspend fun createUser(userData : User, password : String) : Int
    suspend fun saveUserData(userData : User, profileImage : String) : Int
    suspend fun uploadProfileImage(userData: User, profileImage: Uri) : String?

    suspend fun login(email : String, password: String) : User?
    suspend fun getUserDataByEmail(email: String) : User?
}