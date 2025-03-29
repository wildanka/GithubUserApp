package com.example.hcsgithubuser.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arch.base.presentation.BaseFragment
import com.example.hcsgithubuser.databinding.FragmentHomeBinding
import com.example.hcsgithubuser.home.presentation.adapter.GithubUserPagingAdapter
import com.example.hcsgithubuser.search.presentation.SearchFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : com.example.arch.base.presentation.BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModel()

    companion object {
        fun newInstance(bundle: Bundle?): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)


    override fun initView() {
        viewModel.initialisation(null)
        val adapter = GithubUserPagingAdapter()
        binding.rvUserList.adapter = adapter

        lifecycleScope.launch {
            viewModel.items.collectLatest { it ->
                adapter.submitData(it)
            }
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { isLoading ->
                    binding.srlUserList.isRefreshing = if(viewModel.page > 1){
                        false
                    }else{
                        isLoading
                    }
                }
            }
        }

        binding.srlUserList.setOnRefreshListener {
            viewModel.refreshList()
        }

        binding.rvUserList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!viewModel.isLoading.value) {
                    val lastVisible =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val currentListSize = adapter.itemCount

                    if (lastVisible >= currentListSize - 1 && !viewModel.isLoading.value) {
                        // Trigger the next page API call
                        viewModel.loadUserDataRemotely()
                    }
                }
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.cvSearch.setOnClickListener {
            val dialog = SearchFragment()
            dialog.show(parentFragmentManager, "tag")
        }

    }
}