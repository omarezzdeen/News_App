package com.example.news_app.ui.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.news_app.R
import com.example.news_app.databinding.FragmentHomeBinding
import com.example.news_app.domain.model.Article
import com.example.news_app.domain.sealed.State
import com.example.news_app.listener.NewsListener
import com.example.news_app.ui.adapter.HomeAdapter
import com.example.news_app.web_services.DataManger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(), NewsListener {

    override var LOG_TAG = "HOME_FRAGMENT"


    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate


    override fun addCallBacks() {
        getPostData()
    }

    private fun getPostData() {

        lifecycleScope.launch(Dispatchers.Main) {
            DataManger.getNews().collect { state ->
                when (state) {
                    is State.Loading -> {
                        binding.progressBar.visibility = ViewGroup.VISIBLE
                    }

                    is State.Success -> {
                        binding.progressBar.visibility = ViewGroup.GONE
                        state.data?.let {
                            val adapter = HomeAdapter(it.articles, this@HomeFragment)
                            binding.recyclerView.adapter = adapter
                        }
                    }

                    is State.Error -> {
                        binding.progressBar.visibility = ViewGroup.GONE
                        state.message.let {
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    override fun newsListener(news: Article) {
        news.let {
            val detailsFragment = DetailsFragment.newInstance(news)
            replaceFragment(detailsFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_main, fragment)
            addToBackStack(null)
        }.commit()

    }

}