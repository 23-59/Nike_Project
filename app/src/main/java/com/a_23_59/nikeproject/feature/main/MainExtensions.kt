package com.a_23_59.nikeproject.feature.main

import android.content.res.Configuration
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.a_23_59.nikeproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

fun MainActivity.mSetupNavigation(){

    val bottomNav: BottomNavigationView =findViewById(R.id.main_bottom_navigation)

    val navHostFragment=findNavController(R.id.fragmentContainerView3)

    bottomNav.selectedItemId= R.id.mainFragment

    bottomNav.setupWithNavController(navHostFragment)

}

