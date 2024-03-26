package com.morkurensky.payplus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.morkurensky.payplus.data.Item
import com.morkurensky.payplus.databinding.RowItemBinding

class ItemsAdapter() : ListAdapter<Item, ItemsAdapter.ItemsViewHolder>(
    DiffCallback
) {

    var categoryItemClickListener: CategoryItemClickListener? = null

    class ItemsViewHolder(private var binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.item = item
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            categoryItemClickListener?.let {
                it.onCategoryItemClicked(item)
            }
        }
    }

    interface CategoryItemClickListener {
        fun onCategoryItemClicked(item: Item)
    }

    fun setOnCategoryClick(categoryItemClickListener: CategoryItemClickListener) {
        this.categoryItemClickListener = categoryItemClickListener
    }
}