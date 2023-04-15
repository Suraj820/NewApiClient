package com.example.newapiclient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.newapiclient.data.util.Resource
import com.example.newapiclient.databinding.FragmentNewsBinding
import com.example.newapiclient.presentation.adapter.NewsAdapter
import com.example.newapiclient.presentation.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private  lateinit var viewModel: NewsViewModel
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val country = "in"
    private var page = 1
    private var isScrolling = false
    private var isLoading  = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("selected_article",it.url)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,bundle)

        }
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
                        if (it.totalResults%20 == 0){
                            val pages = it.totalResults/20
                        }else{
                            pages = it.totalResults/20
                        }
                        isLastPage = page == pages


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
       // newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager =LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }
    private fun showProgressBar(){
        isLoading = true
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        isLoading = false
        fragmentNewsBinding.progressBar.visibility = View.GONE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentNewsBinding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && isLastPage && hasReachedToEnd && isScrolling
            if(shouldPaginate){
                page++
                Log.e("TAG", "onScrolled:2 ", )
                viewModel.getNewsHeadlines(country,page)
                isScrolling = false

            }


        }
    }
}