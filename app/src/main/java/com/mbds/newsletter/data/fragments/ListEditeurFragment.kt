package com.mbds.newsletter.data.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R
import com.mbds.newsletter.data.adapters.ListButtonAdapter
import com.mbds.newsletter.data.EditorRepository
import com.mbds.newsletter.data.adapters.SourceHandler
import com.mbds.newsletter.models.EditeurReponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListEditeurFragment : Fragment(), SourceHandler {
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
        getEditeurs()

    }
    private fun getEditeurs() {
        lifecycleScope.launch(Dispatchers.IO) {
            val editeurs = EditorRepository.getInstance().getEditeur()
            bindData(editeurs)
        }
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(editeurs: EditeurReponse) {
        lifecycleScope.launch(Dispatchers.Main) {
            //créer l'adapter
            //associer l'adapteur au recyclerview
            val adapter = ListButtonAdapter(editeurs, this@ListEditeurFragment)
            recyclerView.adapter = adapter
        }
    }

    override fun showSource(source: String) {
        (activity as? NavigationListener)?.let {
            it.showFragment(SourceArticleFragment(source))
            it.updateTitle(R.string.list_articles)
        }
    }
}
