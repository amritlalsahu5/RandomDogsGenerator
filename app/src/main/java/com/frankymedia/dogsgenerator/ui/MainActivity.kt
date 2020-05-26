package com.frankymedia.dogsgenerator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.frankymedia.dogsgenerator.R

/*
    Main launching Activity of the application
    with Navigation controller
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controll = this.findNavController(R.id.my_nav_host)
        NavigationUI.setupActionBarWithNavController(this, controll)
    }

    override fun onSupportNavigateUp(): Boolean {
        val controller = this.findNavController(R.id.my_nav_host)
        return controller.navigateUp()
    }
}
