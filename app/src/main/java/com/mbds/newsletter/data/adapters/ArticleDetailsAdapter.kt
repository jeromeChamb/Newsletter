package com.mbds.newsletter.data.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mbds.newsletter.R
import com.mbds.newsletter.data.DB.FavDB
import com.mbds.newsletter.models.Article
import java.text.SimpleDateFormat
import java.util.*

class ArticleDetailsAdapter (
    private val context: Context, items: Article, val handler: ListArticlesHandler
) : RecyclerView.Adapter<ArticleDetailsAdapter.ViewHolder>() {
    private val article: Article = items
    private lateinit var favDB: FavDB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        favDB = FavDB(context)
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.articles_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.mArticleTitle.text = article.title
        holder.mArticleDescription.text = article.description
        holder.mArticleName.text    = article.author
        val sdfOut = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = article.publishedAt
        val dateString = sdfOut.format(date)
        holder.mArticleDate.text = dateString
        val sdfPattern = SimpleDateFormat("yyMMddHHmmssSSS")
        val dateId: Date = article.publishedAt
        val idString = sdfPattern.format(dateId)
        article.id = idString
        readCursorData(article, holder)


        //Button on Click
        holder.mArticleFavorite.setOnClickListener {
            if (article.favorite == 0 ){
                holder.mArticleFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                article.favorite = 1
                favDB.insertIntoTheDatabase(
                    if (article.id!=null) article.id else "",
                    if (article.title!=null) article.title else "",
                    if (article.description!=null) article.description else "",
                    if (article.author!=null) article.author else "",
                    if (article.urlToImage!=null) article.urlToImage else "",
                    if (article.url!=null) article.url else "",
                    1)

            }
            else
            {
                article.favorite = 0
                favDB.remove_fav(article.id)
                holder.mArticleFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
        holder.mArticleRetour.setOnClickListener {
            handler.back()
        }
        holder.mArticleEditeur.text = article.url
        holder.mArticleEditeur.setOnClickListener {
            handler.showPage(article.url)
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
        val mArticleEditeur : TextView
        val mArticleFavorite : ImageButton

        init {
            // Enable click on item
            mArticleAvatar = view.findViewById(R.id.item_list_avatar)
            mArticleName = view.findViewById(R.id.item_list_author)
            mArticleTitle = view.findViewById(R.id.item_list_name)
            mArticleDate = view.findViewById(R.id.item_list_date)
            mArticleDescription = view.findViewById(R.id.item_list_desc)
            mArticleRetour = view.findViewById(R.id.item_back)
            mArticleEditeur = view.findViewById(R.id.item_list_editeur)
            mArticleFavorite = view.findViewById(R.id.item_list_favorite_button)
        }
    }
    private fun readCursorData(
        article: Article,
        viewHolder: ArticleDetailsAdapter.ViewHolder
    ) {
        val cursor = favDB.read_all_data(article.id)
        val db = favDB.readableDatabase
        try {
            while (cursor.moveToNext()) {
                val item_fav_status =
                    cursor.getInt(cursor.getColumnIndex(FavDB.FAVORITE_STATUS))
                article.favorite = item_fav_status

                //check fav status
                if (item_fav_status != null && item_fav_status == 1) {
                    viewHolder.mArticleFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)

                } else if (item_fav_status != null && item_fav_status == 0) {
                    viewHolder.mArticleFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed) cursor.close()
            db.close()
        }
    }
}