package com.mbds.newsletter.data.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R
import com.mbds.newsletter.data.ArticleRepository
import com.mbds.newsletter.data.adapters.ListArticleAdapter
import com.mbds.newsletter.data.adapters.ListHomeAdapter
import com.mbds.newsletter.data.adapters.ListHomeHandler
import com.mbds.newsletter.models.ArticleReponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListHomeFragment (subject: String) : Fragment(), ListHomeHandler {
    private lateinit var recyclerView: RecyclerView
    val subject = subject
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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArticles(subject)
    }
    /**
     * Récupère la liste des articles dans un thread secondaire
     */
    private fun getArticles(subject: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val articles = ArticleRepository.getInstance().getArticles(subject)
            bindData(articles)
        }
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(articles: ArticleReponse) {
        lifecycleScope.launch(Dispatchers.Main) {
            //créer l'adapter
            //associer l'adapteur au recyclerview
            val adapter = ListHomeAdapter(articles,this@ListHomeFragment)
            recyclerView.adapter = adapter
        }
    }

    override fun openList() {
        (activity as? NavigationListener)?.let {
            it.showFragment(ListArticlesFragment("actuality"))
            it.updateTitle(R.string.list_articles)
        }
    }
}