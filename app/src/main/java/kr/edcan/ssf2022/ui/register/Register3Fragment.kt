package kr.edcan.ssf2022.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import gun0912.tedimagepicker.builder.TedImagePicker
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.databinding.FragmentRegister3Binding
import kr.edcan.ssf2022.ui.main.MainActivity
import kr.edcan.ssf2022.util.State

class Register3Fragment : Fragment() {
    lateinit var binding: FragmentRegister3Binding
    val viewModel: Register3ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register3, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.run {

            btnRegister3Next.setOnClickListener {
                (activity as RegisterActivity).run {
//                    if(viewModel.state.value == State.LOADING){
//                        Toast.makeText(context, "회원가입 진행중 입니다.", Toast.LENGTH_SHORT).show()
//                        return@setOnClickListener
//                    }
                    inputMessage = this@Register3Fragment.viewModel.message.value!!

                    register()
                }
            }

            imgRegister3Profile.setOnClickListener {
                inputProfileImage()
            }

            btnRegister4GetImage.setOnClickListener {
                inputProfileImage()
            }
        }


        return binding.root
    }

    private fun inputProfileImage() {
        TedImagePicker.with(context!!)
            .start { uri ->
                (activity as RegisterActivity).inputProfileImage = uri
                Glide.with(context!!)
                    .load(uri)
                    .into(binding.imgRegister3Profile)
            }
    }
}