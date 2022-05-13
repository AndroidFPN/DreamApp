package com.androidfpn.dreamapp.screen.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidfpn.dreamapp.R
import com.androidfpn.dreamapp.data.locale.entity.Sound
import com.androidfpn.dreamapp.databinding.RowRecyclerBinding

class HomeAdapter(private val onClick: (Sound) -> Unit) : ListAdapter<Sound, HomeAdapter.HomeViewHolder>(HomeDiffCallback) {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_recycler, parent, false)
        context = parent.context;
        return HomeViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.let {
            var imageId = 0
            context?.let { context ->
                imageId = context.resources.getIdentifier(getItem(position).image, "drawable", context.packageName)
            }
            it.binding.imageView.setImageResource(imageId)
            it.binding.textView.text = getItem(position).title
        }
    }

    class HomeViewHolder(view: View, val onClick: (Sound) -> Unit) :
        RecyclerView.ViewHolder(view) {

        val binding = RowRecyclerBinding.bind(view)
        private var currentSound: Sound? = null

        init {
            itemView.setOnClickListener {
                currentSound?.let {
                    onClick(it)
                }
            }
        }
    }

    object HomeDiffCallback : DiffUtil.ItemCallback<Sound>() {
        override fun areItemsTheSame(oldItem: Sound, newItem: Sound): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Sound, newItem: Sound): Boolean {
            return oldItem.id == newItem.id
        }
    }
}