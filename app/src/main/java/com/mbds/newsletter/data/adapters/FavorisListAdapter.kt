package com.mbds.newsletter.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.models.Favoris

class FavorisListAdapter : ListAdapter<Favoris, FavorisListAdapter.FavorisViewHolder>(FavorisComparator())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorisViewHolder {
        return FavorisViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavorisViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.favoris)
    }

    class FavorisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val favorisItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            favorisItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): FavorisViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return FavorisViewHolder(view)
            }
        }
    }

    class FavorisComparator : DiffUtil.ItemCallback<Favoris>() {
        override fun areItemsTheSame(oldItem: Favoris, newItem: Favoris): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Favoris, newItem: Favoris): Boolean {
            return oldItem.favoris == newItem.favoris
        }
    }

}