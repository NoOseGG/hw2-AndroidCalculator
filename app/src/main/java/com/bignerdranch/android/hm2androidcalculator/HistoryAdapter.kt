package com.bignerdranch.android.hm2androidcalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.hm2androidcalculator.databinding.HistoryFragmentBinding
import com.bignerdranch.android.hm2androidcalculator.databinding.ItemHistoryBinding

class HistoryAdapter(private val history: Array<String>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.tvItemHistory.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistoryViewHolder(
            binding = ItemHistoryBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(history[position])
    }

    override fun getItemCount(): Int {
        return history.size
    }
}