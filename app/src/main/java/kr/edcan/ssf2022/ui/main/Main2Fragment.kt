package kr.edcan.ssf2022.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.databinding.FragmentMain2Binding

class Main2Fragment : Fragment() {
    lateinit var binding : FragmentMain2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main2, container, false)

        val diaryListAdapter = DiaryListAdapter((activity as MainActivity).viewModel.diaryList.value!!)
        binding.listMain2DiaryList.adapter = diaryListAdapter


        return binding.root
    }
}