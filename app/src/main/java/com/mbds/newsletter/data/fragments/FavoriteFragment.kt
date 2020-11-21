package com.mbds.newsletter.data.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R
import com.mbds.newsletter.data.DB.FavDB
import com.mbds.newsletter.data.adapters.ArticleDetailsAdapter
import com.mbds.newsletter.data.adapters.FavoriteAdapter
import com.mbds.newsletter.data.adapters.ListArticleAdapter
import com.mbds.newsletter.data.adapters.ListArticlesHandler
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleReponse
import com.mbds.newsletter.models.FavItem

class FavoriteFragment : Fragment(), ListArticlesHandler{
    private lateinit var recyclerView: RecyclerView
    private lateinit var favDB: FavDB
    private var favoriteList: MutableList<FavItem> = ArrayList<FavItem>()
   // private lateinit var favAdapter: ListFavArticlesAdapter
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_articles_fragment, container, false)
        recyclerView = view.findViewById(R.id.articles_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        favDB = FavDB(activity)
        loadData(requireContext())
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.favorite)
        }
    }




    private fun loadData(context: Context) {
        if (favoriteList != null) {
            favoriteList.clear()
        }
        val db = favDB.readableDatabase
        val cursor = favDB.select_all_favorite_list()
        try {
            while (cursor.moveToNext()) {
                val title = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_TITLE))
                val url = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_URL))
                val description = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_DESCRIPTION))
                val author = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_AUTHOR))
                val id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID))
                val urlToImage = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_IMAGE))

                val favArticle = FavItem(id, title,url, description, author, urlToImage)
                favoriteList.add(favArticle)
            }
        } finally {
            if (cursor != null && cursor.isClosed) cursor.close()
            db.close()
        }
        val adapter = FavoriteAdapter(requireContext(), this,favoriteList)
        recyclerView.adapter = adapter
    }

    override fun showArticle(article: Article) {
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.article_details)
        }
        val adapter = ArticleDetailsAdapter(requireContext() ,article, this)
        recyclerView.adapter = adapter
    }

    override fun back() {
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.favorite)
        }
        val adapter = FavoriteAdapter(requireContext(), this,favoriteList)
        recyclerView.adapter = adapter

    }

    override fun showPage(url: String) {
        val chemin: Uri = Uri.parse(url)
        val naviguer = Intent(Intent.ACTION_VIEW, chemin)
        startActivity(naviguer)
    }

}