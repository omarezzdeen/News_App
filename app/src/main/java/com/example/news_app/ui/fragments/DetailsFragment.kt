package com.example.news_app.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.news_app.databinding.FragmentDetailsBinding

class DetailsFragment: BaseFragment<FragmentDetailsBinding>() {

    override var LOG_TAG = "DETAILS_FRAGMENT"

    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun addCallBacks() {
        TODO("Not yet implemented")
    }
}