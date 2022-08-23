package kr.edcan.ssf2022.ui.diaryDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.databinding.ActivityDiaryDetailBinding
import kr.edcan.ssf2022.model.data.Diary
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.util.ExtraKey

class DiaryDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDiaryDetailBinding
    val viewModel : DiaryDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_diary_detail)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        viewModel.diaryDate.value = intent.getParcelableExtra<Diary>(ExtraKey.diaryData)

        binding.tbDetail.setNavigationOnClickListener {
            finish()
        }
    }
}