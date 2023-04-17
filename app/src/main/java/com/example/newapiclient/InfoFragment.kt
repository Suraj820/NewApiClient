package com.example.newapiclient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.newapiclient.databinding.FragmentInfoBinding
import com.example.newapiclient.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment() {
    lateinit var fragmentInfoBinding: FragmentInfoBinding
    lateinit var  viewModel : NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        val args : InfoFragmentArgs by navArgs()

        val article = args.selectedArtical
        viewModel = (activity as MainActivity).viewModel
        fragmentInfoBinding.wvInfo.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }

        }

        fragmentInfoBinding.fabSave.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view,"Saved This for offline",Snackbar.LENGTH_LONG).show()
        }
    }

}