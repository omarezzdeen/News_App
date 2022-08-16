package com.example.news_app.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.news_app.databinding.FragmentHomeBinding
import com.example.news_app.ui.adapter.HomeAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override var LOG_TAG = "HOME_FRAGMENT"

    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate


    override fun addCallBacks() {
        val adapter = HomeAdapter()
        binding.recyclerView.adapter = adapter
    }


}