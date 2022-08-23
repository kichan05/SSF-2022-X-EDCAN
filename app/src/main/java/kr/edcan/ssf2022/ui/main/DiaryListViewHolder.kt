package kr.edcan.ssf2022.ui.main

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kr.edcan.ssf2022.databinding.RowDiaryListItemBinding
import kr.edcan.ssf2022.ui.diaryDetail.DiaryDetailActivity
import kr.edcan.ssf2022.util.ExtraKey

class DiaryListViewHolder(val binding : RowDiaryListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context, DiaryDetailActivity::class.java).apply {
                putExtra(ExtraKey.diaryData, binding.diaryData)
            }
            binding.root.context.startActivity(intent)
        }
    }
}