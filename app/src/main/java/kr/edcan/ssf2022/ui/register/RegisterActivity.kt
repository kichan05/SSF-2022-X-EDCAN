package kr.edcan.ssf2022.ui.register

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.util.State

class RegisterActivity : AppCompatActivity() {
    lateinit var navController: NavController
    val viewModel : RegisterViewModel by viewModels()

    lateinit var loadingDialog: AlertDialog

    // 사용자가 입력한 값들이 들어오는 변수
    var inputName = ""
    var inputEmail = ""
    var inputPassword = ""
    var inputProfileImage : Uri? = null
    var inputMessage = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        navController = findNavController(R.id.fr_register)

        val dialogView = layoutInflater.inflate(R.layout.dialog_loading, null)
        loadingDialog = AlertDialog.Builder(this).setView(dialogView).create().apply {
            setCancelable(false)
        }

        viewModel.state.observe(this){
            when(it){
                State.SUCCESS -> {
                    Toast.makeText(this, "회원가입에 성공 했습니다.", Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                    finish()
                }
                State.LOADING -> {
//                    Toast.makeText(this, "회원가입에 중", Toast.LENGTH_SHORT).show()
                    loadingDialog.show()
                }
                State.FAIL -> {
                    Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                }
            }
        }
    }

    fun register(){
        /*
        * 사용자가 Reguster3에서 회원가입 완료 버튼을 눌렀을때 실행되는 함수
        * */
        viewModel.userData.value = User(
            name = inputName,
            email = inputEmail,
            message = inputMessage,
            profileImage = ""
        )

        viewModel.profile.value = inputProfileImage
        viewModel.password.value = inputPassword

        viewModel.register()
    }
}