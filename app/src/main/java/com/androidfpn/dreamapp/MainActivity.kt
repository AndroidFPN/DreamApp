package com.androidfpn.dreamapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController : NavController =Navigation.findNavController(this,R.id.activity_main_nav_host_fragment)
        val bottomNavigationView: BottomNavigationView=findViewById(R.id.activity_main_bottom_navigation_view)
//        NavigationUI.setupWithNavController(navController = navController)
    }
}