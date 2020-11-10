package com.mbds.newsletter.data.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mbds.newsletter.NavigationListener
import com.mbds.newsletter.R

class AboutUsFragment :Fragment() {
    lateinit var home : FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.abouts_us, container, false)
        home = view.findViewById(R.id.btn_home)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(HomePageFragment())
                it.updateTitle(R.string.home)
            }
        }
    }
}