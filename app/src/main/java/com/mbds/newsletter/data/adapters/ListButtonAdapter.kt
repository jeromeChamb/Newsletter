package com.mbds.newsletter.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.models.Article
import com.mbds.newsletter.models.ArticleReponse
import com.mbds.newsletter.models.Editeur
import com.mbds.newsletter.models.EditeurReponse

class ListButtonAdapter (items : EditeurReponse) : RecyclerView.Adapter<ListButtonAdapter.ViewHolder>(){
    private val mEditeurs: EditeurReponse = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.button_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val editeur: Editeur = mEditeurs.editeur[position]
        holder.mButton.text = editeur.name
    }

    override fun getItemCount(): Int {
        return mEditeurs.editeur.size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mButton: Button


        init {
            // Enable click on item
            mButton = view.findViewById(R.id.btn_item)

        }
    }
}