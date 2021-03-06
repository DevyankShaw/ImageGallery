package com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.R
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle =
            ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
            )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                HomeFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                HomeFragment()
            ).commit()
        }
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

}
