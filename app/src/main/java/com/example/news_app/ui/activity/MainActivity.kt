package com.example.news_app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.news_app.R
import com.example.news_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}