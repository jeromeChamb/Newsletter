package com.mbds.newsletter.data.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R
import com.mbds.newsletter.data.adapters.ListCategoryAdapter
import com.mbds.newsletter.data.adapters.SourceHandler


class ListCategoryFragment : Fragment(), SourceHandler{
    private lateinit var recyclerView: RecyclerView

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_button, container, false)
        recyclerView = view.findViewById(R.id.list_button)
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL, false)
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
        val adapter = ListCategoryAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun showSource(source: String) {
        (activity as? NavigationListener)?.let {
            it.showFragment(CategoryArticlesFragment(source))
            it.updateTitle(R.string.list_articles)
        }
    }


}