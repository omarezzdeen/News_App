package com.example.news_app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.databinding.FragmentDetailsBinding
import com.example.news_app.domain.model.Article
import com.example.news_app.domain.model.News
import com.example.news_app.domain.sealed.State
import com.example.news_app.ui.adapter.HomeAdapter
import com.example.news_app.util.Constants
import com.example.news_app.web_services.DataManger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragment: BaseFragment<FragmentDetailsBinding>() {

    override var LOG_TAG = "DETAILS_FRAGMENT"

    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun addCallBacks() {
        getDetailsData()
    }

    private fun getDetailsData() {

        lifecycleScope.launch(Dispatchers.Main) {
            DataManger.getNews().collect { state ->
                when(state){
                    is State.Loading -> {
                        binding.apply {
                            textDescription.text = ""
                            textTitle.text = ""
                            imageDetails.setImageResource(R.drawable.ic_android_black)
                        }
                        binding.progressBar.visibility = ViewGroup.VISIBLE
                    }

                    is State.Success -> {
                        binding.progressBar.visibility = ViewGroup.GONE
                        state.data?.let {
                            step()
                        }
                    }

                    is State.Error -> {
                        binding.progressBar.visibility = ViewGroup.GONE
                        state.message.let {
                            Log.i(LOG_TAG, it.toString())
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun step(){
        val article : Article = arguments?.getSerializable(Constants.Data.ID_KEY) as Article
        buildNews(article)
    }

    private fun buildNews(article: Article){
        binding.apply {
            textDescription.text = article.description
            textTitle.text = article.title
            Glide.with(this@DetailsFragment)
                .load(article.urlToImage)
                .into(imageDetails)
        }
    }

    companion object {
        fun newInstance(article: Article): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(Constants.Data.ID_KEY, article)
                }
            }
        }
    }

}