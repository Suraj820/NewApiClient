package com.example.newapiclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapiclient.data.util.Resource
import com.example.newapiclient.databinding.FragmentNewsBinding
import com.example.newapiclient.presentation.adapter.NewsAdapter
import com.example.newapiclient.presentation.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private  lateinit var viewModel: NewsViewModel
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val country = "in"
    private val page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
       viewModel.getNewsHeadlines(country,page)
        viewModel.newsHeadlines.observe(viewLifecycleOwner) { response ->
            when (response) {

                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                    }

                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.data?.let {
                        Toast.makeText(activity, "Some went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                else -> {}
            }

        }
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager =LinearLayoutManager(activity)
        }
    }
    private fun showProgressBar(){
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        fragmentNewsBinding.progressBar.visibility = View.GONE
    }
}