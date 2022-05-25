package com.androidfpn.dreamapp.screen.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidfpn.dreamapp.R
import com.androidfpn.dreamapp.data.locale.entity.SoundCategories
import com.androidfpn.dreamapp.databinding.HomeCategoryItemBinding

class HomeCategoryAdapter(private val onClick: (SoundCategories) -> Unit) :
    ListAdapter<SoundCategories, HomeCategoryAdapter.CategoryViewHolder>(CategoryDiffCallback) {

    private lateinit var context: Context

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = HomeCategoryItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_category_item, parent, false)
        context = parent.context
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.let {
            val currentCategory = getItem(position)
            it.categoryTextView.text = currentCategory.name
            it.root.setOnClickListener {
                onClick(currentCategory)
            }
            val imageId = context.resources.getIdentifier(
                getItem(position).image,
                "drawable",
                context.packageName
            )
            it.categoryIcon.setImageResource(imageId)
        }
    }

    object CategoryDiffCallback : DiffUtil.ItemCallback<SoundCategories>() {
        override fun areItemsTheSame(oldItem: SoundCategories, newItem: SoundCategories): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SoundCategories,
            newItem: SoundCategories
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }
}