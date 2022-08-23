package kr.edcan.ssf2022.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.databinding.FragmentMain1Binding
import kr.edcan.ssf2022.model.data.Diary
import kr.edcan.ssf2022.ui.diaryDetail.DiaryDetailActivity
import kr.edcan.ssf2022.ui.write.WriteActivity
import kr.edcan.ssf2022.util.ExtraKey
import java.util.Date

class Main1Fragment : Fragment() {
    lateinit var binding: FragmentMain1Binding
    val viewModel: Main1ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main1, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        loading()

        with(binding){
            btnMain1GotoWrite.setOnClickListener {
                val intent = Intent(context, WriteActivity::class.java)
                intent.putExtra(ExtraKey.userData, (activity as MainActivity).viewModel.userData.value)
                activity!!.startActivity(intent)
            }

            txtMain1Calendar.setOnDateChangeListener { v, year, month, day ->
                this@Main1Fragment.viewModel.selectedData.value = Date(year - 1900, month, day)
            }

            btnMain1GotodiaryDetail.setOnClickListener {
                val intent = Intent(context, DiaryDetailActivity::class.java).apply {
                    putExtra(ExtraKey.diaryData, this@Main1Fragment.viewModel.selectedDiary.value)
                }
                activity!!.startActivity(intent)
            }
        }

        viewModel.selectedData.observe(viewLifecycleOwner) {
            reLoadSelectedDiary(it)
        }

        return binding.root
    }

    private fun loading() {
        reLoadTitleMessage()
        reLoadSelectedDiary(viewModel.selectedData.value!!)
    }

    private fun reLoadTitleMessage() {
        val now = Date()
        binding.txtMain1Message.text = if(
            (activity as MainActivity).viewModel.diaryList.value!!.any { diaryListItem ->
                diaryListItem.date.year == now.year && diaryListItem.date.month == now.month && diaryListItem.date.date == now.date
            }
        ){
            "오늘 일기를 작성했어요"
        }
        else {
            "아직 오늘 일기를 작성하지 않았어요"
        }
    }

    private fun reLoadSelectedDiary(date: Date) {
        val filter = (activity as MainActivity).viewModel.diaryList.value!!.filter { diaryListItem ->
            diaryListItem.date.year == date.year && diaryListItem.date.month == date.month && diaryListItem.date.date == date.date
        }

        viewModel.isSelectedDataDiary.value = filter.isNotEmpty()

        if (filter.isNotEmpty()) {
            viewModel.selectedDiary.value = filter[0]
        }
    }
}