package com.example.rickandmorty.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rickandmorty.ui.theme.RickAndMortyComposeTheme
import com.example.rickandmortycompose.ui.navgation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyComposeTheme {
                Navigation()
            }
        }
    }
}