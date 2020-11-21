package com.mbds.newsletter.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.data.DB.FavDB
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.FavItem
import com.mbds.newsletter.models.Source
import java.text.SimpleDateFormat
import java.util.*

class FavoriteAdapter(private val context: Context, val handler: ListArticlesHandler, private var favoriteList : MutableList<FavItem>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var favDB: FavDB


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        favDB = FavDB(context)
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.articles_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: FavItem = favoriteList[position]
        var details = Article("",Source("",""),article.author,article.title,
        article.description,article.url, article.urlToImage,Date(),"",1)

        // Display Neighbour Name
        holder.mArticleTitle.text = article.title

        holder.mArticleDescription.text = article.description
        holder.mArticleName.text    = article.author

        holder.mArticleFavorite.setImageResource(R.drawable.ic_baseline_delete_24)

        holder.mArticleTitle.setOnClickListener {
            handler.showArticle(details)
        }
        holder.mArticleDescription.setOnClickListener {
            handler.showArticle(details)
        }
        holder.mArticleFavorite.setOnClickListener(View.OnClickListener {
            article.id?.let { it1 -> favDB.remove_fav(it1) }
            removeItem(position)
        })


        // Display  Avatar
        Glide.with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
            .skipMemoryCache(false)
            .into(holder.mArticleAvatar)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mArticleAvatar: ImageView
        val mArticleName: TextView
        val mArticleTitle: TextView
        val mArticleDate: TextView
        val mArticleDescription: TextView
        val mArticleFavorite: ImageButton

        init {
            // Enable click on item
            mArticleAvatar = view.findViewById(R.id.item_list_avatar)
            mArticleName = view.findViewById(R.id.item_list_author)
            mArticleTitle = view.findViewById(R.id.item_list_name)
            mArticleDate = view.findViewById(R.id.item_list_date)
            mArticleDescription = view.findViewById(R.id.item_list_desc)
            mArticleFavorite = view.findViewById(R.id.item_list_favorite_button)
        }
    }

    private fun removeItem(position: Int){
        favoriteList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, favoriteList.size)
    }
}