package com.example.news_app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.news_app.R
import com.example.news_app.databinding.ActivityMainBinding
import com.example.news_app.ui.fragments.DetailsFragment
import com.example.news_app.ui.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment()
    }


    private fun showFragment(){
        val myFragment = HomeFragment()
        myFragment.arguments
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_main, myFragment)
        transaction.commit()
    }
}