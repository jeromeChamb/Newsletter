package com.mbds.newsletter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mbds.newsletter.data.fragments.AboutUsFragment
import com.mbds.newsletter.data.fragments.FavoriteFragment
import com.mbds.newsletter.data.fragments.HomePageFragment
import com.mbds.newsletter.data.fragments.ListArticlesFragment


class MainActivity : AppCompatActivity(),
    NavigationListener {
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        showFragment(HomePageFragment())
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun showFragmentinFragment(id:Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(id, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu.findItem(R.id.btn_home_toolbar).isVisible = true
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if(p0.isNullOrBlank()){
                    //afficher un label recherche vide
                }else{
                    showFragment(ListArticlesFragment(p0))
                }
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.btn_a_propos -> {
                showFragment(AboutUsFragment())
                updateTitle(R.string.aPropos)
                true
            }
            R.id.btn_favorite_toolbar -> {
                showFragment(FavoriteFragment())
                updateTitle(R.string.favorite)
                true
            }
            R.id.btn_home_toolbar -> {
                showFragment(HomePageFragment())
                updateTitle(R.string.home)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}