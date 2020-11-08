package com.mbds.newsletter.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mbds.newsletter.R
import com.mbds.newsletter.models.Article

class ArticleDetailsAdapter (
    items: Article, val handler: ListArticlesHandler
) : RecyclerView.Adapter<ArticleDetailsAdapter.ViewHolder>() {
    private val article: Article = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.articles_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Display Neighbour Name
        holder.mArticleTitle.text = article.title
        holder.mArticleDescription.text = article.description
        holder.mArticleName.text    = article.author
        holder.mArticleDate.text = article.publishedAt
        holder.mArticleRetour.setOnClickListener {
            handler.back()
        }
        val context = holder.itemView.context
        // Display  Avatar
        Glide.with(context)
            .load(article.urlToImage)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
            .skipMemoryCache(false)
            .into(holder.mArticleAvatar)
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mArticleAvatar: ImageView
        val mArticleName: TextView
        val mArticleTitle: TextView
        val mArticleDate: TextView
        val mArticleDescription: TextView
        val mArticleRetour : ImageButton

        init {
            // Enable click on item
            mArticleAvatar = view.findViewById(R.id.item_list_avatar)
            mArticleName = view.findViewById(R.id.item_list_author)
            mArticleTitle = view.findViewById(R.id.item_list_name)
            mArticleDate = view.findViewById(R.id.item_list_date)
            mArticleDescription = view.findViewById(R.id.item_list_desc)
            mArticleRetour = view.findViewById(R.id.item_back)
        }
    }
}