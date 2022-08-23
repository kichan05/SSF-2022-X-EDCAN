package kr.edcan.ssf2022.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.edcan.ssf2022.databinding.RowDiaryListItemBinding
import kr.edcan.ssf2022.model.data.Diary

class DiaryListAdapter(val items : List<Diary>) : RecyclerView.Adapter<DiaryListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowDiaryListItemBinding.inflate(layoutInflater, parent, false)

        return DiaryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryListViewHolder, position: Int) {
        holder.binding.diaryData = items[position]
    }

    override fun getItemCount(): Int = items.size
}