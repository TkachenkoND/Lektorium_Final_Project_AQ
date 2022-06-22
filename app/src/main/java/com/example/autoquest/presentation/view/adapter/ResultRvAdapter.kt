package com.example.autoquest.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.autoquest.R
import com.example.autoquest.databinding.QuestItemRvBinding
import com.example.autoquest.databinding.ResultItemRvBinding
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.UserResultModel

class ResultRvAdapter :
    ListAdapter<UserResultModel, ResultRvAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResultItemRvBinding.inflate(inflater, parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: ResultItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: UserResultModel) {
            binding.apply {
                userName.text = result.userName
                userPoint.text = String.format("%.2f",result.userPoint)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserResultModel>() {

        override fun areItemsTheSame(oldItem: UserResultModel, newItem: UserResultModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: UserResultModel,
            newItem: UserResultModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}