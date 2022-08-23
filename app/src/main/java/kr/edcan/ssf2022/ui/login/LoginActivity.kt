package kr.edcan.ssf2022.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.databinding.ActivityLoginBinding
import kr.edcan.ssf2022.ui.main.MainActivity
import kr.edcan.ssf2022.ui.register.RegisterActivity
import kr.edcan.ssf2022.util.Pattern
import kr.edcan.ssf2022.util.State

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.autoLogin()


        // todo Mission 1 : id가 btn_Login_register인 Button을 선택해서 registerBtn에 저장하세요.
        val registerBtn = findViewById<Button>(R.id.btn_login_register)


        /* todo Mission 2 : 선택해온 registerBtn에 Click Evnet를 만들어서 회원가입 화면으로 이동해주세요
        *                   회원가입 화면은 RegisterActivity입니다.   */
        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        // todo Mission 3 : id가 btn_login_login인 Button을 선택해서 loginBtn에 저장하세요.
        val loginBtn = findViewById<Button>(R.id.btn_login_login)


        /* todo Mission 4 : 선택해온 loginBtn에 Click Evnet를 만들어서 로그인을 진행해주세요.
        *                   로그인을 할때는 viewModel.login() 함수를 실행하면 됩니다. */
        loginBtn.setOnClickListener {
            if(checkInput()){
                viewModel.login()
            }
        }


        viewModel.state.observe(this) {
            when (it) {
                State.SUCCESS -> {
                    Log.d("loginLog", "로그인 성공")
                }
                State.LOADING -> {
                    Log.d("loginLog", "로그인 중")
                }
                State.FAIL -> {
                    Log.d("loginLog", "로그인 실패")
                    viewModel.errorMessage.value = "로그인에 실패 했습니다."
                }
            }
        }

        viewModel.userData.observe(this) {
            if (it != null) {
                Toast.makeText(this, "환영합니다", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("userData", it)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun checkInput() : Boolean {
        if (viewModel.inputEmail.value.isNullOrBlank()) {
            viewModel.errorMessage.value = "이메일을 입력해주세요."
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(viewModel.inputEmail.value!!).matches()) {
            viewModel.errorMessage.value = "이메일이 형식에 맞지 않습니다."
            return false
        }
        if (viewModel.inputPassword.value.isNullOrBlank()) {
            viewModel.errorMessage.value = "비밀번호를 입력해주세요."
            return false
        }
        if (viewModel.inputPassword.value!!.length < 8) {
            viewModel.errorMessage.value = "비밀번호는 8글자 이상으로 해주세요."
            return false
        }

        return true
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.autoLogin()
    }
}