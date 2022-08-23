package kr.edcan.ssf2022.ui.register

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.NonDisposableHandle.parent
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.databinding.FragmentRegister1Binding
import kr.edcan.ssf2022.ui.main.MainActivity

class Register1Fragment : Fragment() {
    lateinit var binding: FragmentRegister1Binding
    val viewModel: Register1ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register1, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnRegister1Next.setOnClickListener { // 회원가입 1에서 다음 버튼을 눌렀을때
            if(!Patterns.EMAIL_ADDRESS.matcher(viewModel.email.value).matches()){
                viewModel.errorMessage.value = "이메일이 형식에 맞지 않습니다."
                return@setOnClickListener
            }

            (activity as RegisterActivity).run {
                inputEmail = this@Register1Fragment.viewModel.email.value!!
                inputName = this@Register1Fragment.viewModel.name.value!!
                navController.navigate(R.id.action_register1Fragment_to_register2Fragment)
            }
        }

        viewModel.name.observe(viewLifecycleOwner){
            viewModel.checkNext()
        }

        viewModel.email.observe(viewLifecycleOwner){
            viewModel.checkNext()
        }

        return binding.root
    }
}