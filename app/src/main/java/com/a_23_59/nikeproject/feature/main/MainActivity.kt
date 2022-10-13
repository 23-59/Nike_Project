package com.a_23_59.nikeproject.feature.main

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.a_23_59.nikeproject.R
import com.a_23_59.nikeproject.common.NikeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : NikeActivity() {

    override val rootView: CoordinatorLayout?
        get() = findViewById(R.id.main_coordinator)

    override fun onCreate(savedInstanceState: Bundle?) {

        window.decorView.layoutDirection=View.LAYOUT_DIRECTION_RTL

        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE)

        setContentView(R.layout.activity_main)


    }

    override fun onStart() {

        super.onStart()

        mSetupNavigation() // matching Bottom Navigation with Navigation Component

    }



}

