package com.mbds.newsletter.data.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R


class HomePageFragment : Fragment(){

    lateinit var tousArticles : TextView
    lateinit var aboutUs : Button
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_page, container, false)
        tousArticles = view.findViewById(R.id.text_last_categories)
        aboutUs = view.findViewById(R.id.btn_a_propos)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? NavigationListener)?.let {
            //it.showFragmentinFragment(R.id.fragment_list,ListHomeFragment("actuality"))
            it.showFragmentinFragment(R.id.fragment_list_editor,ListEditeurFragment())
            it.updateTitle(R.string.home)
        }

        tousArticles.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(ListArticlesFragment("actuality"))
                it.updateTitle(R.string.list_articles)
            }
        }

        aboutUs.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AboutUsFragment())
                it.updateTitle(R.string.aPropos)
            }
        }

    }
}