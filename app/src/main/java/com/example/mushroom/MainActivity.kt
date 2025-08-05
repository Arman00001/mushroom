package com.example.mushroom

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            AppNavigation(rememberNavController())
        }
    }
}