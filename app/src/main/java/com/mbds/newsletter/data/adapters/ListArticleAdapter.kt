package com.mbds.newsletter.data.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleReponse
import com.mbds.newsletter.data.DB.FavDB
import java.text.SimpleDateFormat
import java.util.*


class ListArticleAdapter(
    items: ArticleReponse, val handler: ListArticlesHandler, val context : Context
) : RecyclerView.Adapter<ListArticleAdapter.ViewHolder>() {
        private val mArticles: ArticleReponse = items
        private lateinit var favDB: FavDB


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            favDB = FavDB(context)
            //create table on first
            val prefs: SharedPreferences =
                context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val firstStart = prefs.getBoolean("firstStart", true)

        if (firstStart) {
                createTableOnFirstStart()
            }

            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.articles_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val article: Article = mArticles.articles[position]
            val context = holder.itemView.context

            val sdfPattern = SimpleDateFormat("yyMMddHHmmssSSS")
            val dateId: Date = article.publishedAt
            val idString = sdfPattern.format(dateId)
            article.id = idString
            readCursorData(article,holder)
            // Display Neighbour Name
            holder.mArticleTitle.text = article.title
            holder.mArticleDescription.text = article.description
            holder.mArticleName.text    = article.author

            val sdfOut = SimpleDateFormat("dd-MM-yyyy")
            val date: Date = article.publishedAt
            val dateString = sdfOut.format(date)
            holder.mArticleDate.text = dateString



            holder.mArticleTitle.setOnClickListener {
                handler.showArticle(article)
            }
            holder.mArticleDescription.setOnClickListener {
                handler.showArticle(article)
            }
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
            holder.mArticleAvatar.setOnClickListener {
                handler.showArticle(article)
            }
            // Display  Avatar
            Glide.with(context)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_image_24)
                .skipMemoryCache(false)
                .into(holder.mArticleAvatar)
        }

        override fun getItemCount(): Int {
            return mArticles.articles.size
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

    private fun createTableOnFirstStart() {
        val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
    }
    private fun readCursorData(
        article: Article,
        viewHolder: ViewHolder
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