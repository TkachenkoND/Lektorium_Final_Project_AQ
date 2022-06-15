package com.example.autoquest.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.autoquest.R
import com.example.autoquest.databinding.QuestItemRvBinding
import com.example.autoquest.domain.models.QuestItem

interface ClickOnTheItem {
    fun itemPress(questItem: QuestItem)
}

interface ClickOnTheFavorite {
    fun favoritePress(isFavorite: Boolean, questId: Int)
}

class ListOfQuestsAdapter(
    val clickOnTheItem: ClickOnTheItem,
    val clickOnTheFavorite: ClickOnTheFavorite
) :
    ListAdapter<QuestItem, ListOfQuestsAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestItemRvBinding.inflate(inflater, parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: QuestItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(questItem: QuestItem) {

            with(binding) {
                dataQuest.text = questItem.dataQuest
                nameQuest.text = questItem.nameQuest

                if (questItem.isFavorite)
                    favorites.setBackgroundResource(R.drawable.quest_item_star_background_img)
                else
                    favorites.setBackgroundResource(R.drawable.quest_item_star_img)


                Glide.with(itemBackgroundImg.context)
                    .load(questItem.itemBackgroundImg)
                    .error(R.color.white)
                    .into(itemBackgroundImg)

                itemCard.setOnClickListener {
                    clickOnTheItem.itemPress(questItem)
                }

                favorites.setOnClickListener {

                    if (questItem.isFavorite) {
                        favorites.setBackgroundResource(R.drawable.quest_item_star_img)
                        clickOnTheFavorite.favoritePress(false, questItem.questsId)
                    } else {
                        favorites.setBackgroundResource(R.drawable.quest_item_star_background_img)
                        clickOnTheFavorite.favoritePress(true, questItem.questsId)
                    }
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<QuestItem>() {

        override fun areItemsTheSame(oldItem: QuestItem, newItem: QuestItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: QuestItem,
            newItem: QuestItem
        ): Boolean {
            return oldItem == newItem
        }
    }

}