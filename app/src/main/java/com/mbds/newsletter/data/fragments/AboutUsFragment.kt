package com.mbds.newsletter.data.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R

class AboutUsFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.abouts_us, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.btn_favorite_toolbar).isVisible = false
        menu.findItem(R.id.btn_a_propos).isVisible = false
        menu.findItem(R.id.btn_home_toolbar).isVisible = true
        super.onPrepareOptionsMenu(menu)
    }


}