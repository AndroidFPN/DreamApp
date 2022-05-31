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
import javax.inject.Inject

class HomeSoundsAdapter @Inject constructor() :
    ListAdapter<Sound, HomeSoundsAdapter.HomeViewHolder>(HomeDiffCallback) {

    private lateinit var context: Context

    private var onItemClickListener: ((Sound) -> Unit)? = null
    fun setOnItemClickListener(listener: (Sound) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_recycler, parent, false)
        context = parent.context
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.let { binding ->
            val currentSound = getItem(position)
            val imageId =
                context.resources.getIdentifier(currentSound.image, "drawable", context.packageName)
            binding.ivSound.setImageResource(imageId)
            binding.tvSoundName.text = currentSound.title
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(currentSound)
                }
            }
        }
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RowRecyclerBinding.bind(view)
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