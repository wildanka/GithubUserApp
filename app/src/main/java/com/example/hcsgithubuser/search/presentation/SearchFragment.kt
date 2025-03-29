package com.example.hcsgithubuser.search.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hcsgithubuser.base.data.UiState
import com.example.hcsgithubuser.base.presentation.BaseBottomSheetDialogFragment
import com.example.hcsgithubuser.databinding.FragmentSearchBinding
import com.example.hcsgithubuser.home.presentation.adapter.GithubUserListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseBottomSheetDialogFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModel()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)

    override fun initView() {
        val adapter = GithubUserListAdapter()
        binding.rvUserList.adapter = adapter
        binding.rvUserList.post {
            binding.rvUserList.invalidate()
            binding.rvUserList.requestLayout()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userList.collect { uiState ->
                    // New value received
                    when (uiState) {
                        is UiState.Success -> {
                            adapter.refreshList(uiState.data)
                        }

                        is UiState.Error -> {
                            //TODO : handle error UI here
//                            stopLoadingIndicator()
                        }

                        UiState.Loading -> {
//                            if (viewModel.page == 1) {
//                                binding.srlUserList.isRefreshing = true
//                            }
                            //TODO : handle Loading UI here
                        }
                    }
                }
            }
        }

        expandFull(binding.layout, false)
        binding.etSearch.doOnTextChanged { text, start, before, count ->
            if(count>0){
                binding.ivClearQuery.visibility = View.VISIBLE
            }else{
                binding.ivClearQuery.visibility = View.GONE
            }
            viewModel.setQuery(text.toString())
        }

        binding.rvUserList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!viewModel.isLoading.value) {
                    val lastVisible =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    if (lastVisible >= adapter.itemCount - 1 && viewModel.maxResultCount > adapter.itemCount) {
                        viewModel.searchByUsername(binding.etSearch.text.toString())
                    }
                }
            }
        })

        binding.ivClearQuery.setOnClickListener {
            binding.etSearch.setText("")
        }
    }

}