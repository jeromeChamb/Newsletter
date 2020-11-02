package com.mbds.newsletter.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.models.Article

class ListArticleAdapter (
    items: List<Article>
    ) : RecyclerView.Adapter<ListArticleAdapter.ViewHolder>() {
        private val mArticles: List<Article> = items
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.articles_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val article: Article = mArticles[position]
            // Display Neighbour Name
            holder.mArticleName.text = article.name
            holder.mArticleDescription.text = article.description
        }

        override fun getItemCount(): Int {
            return mArticles.size
        }

        class ViewHolder(view: View) :
            RecyclerView.ViewHolder(view) {
            val mArticleAvatar: ImageView
            val mArticleName: TextView
            val mArticleDescription: TextView

            init {
                // Enable click on item
                mArticleAvatar = view.findViewById(R.id.item_list_avatar)
                mArticleName = view.findViewById(R.id.item_list_name)
                mArticleDescription = view.findViewById(R.id.item_list_desc)
            }
        }
}