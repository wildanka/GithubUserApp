package com.example.hcsgithubuser.home.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hcsgithubuser.base.data.UiState
import com.example.hcsgithubuser.base.presentation.BaseFragment
import com.example.hcsgithubuser.databinding.FragmentHomeBinding
import com.example.hcsgithubuser.home.presentation.adapter.GithubUserPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

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
                Log.d("WLDN HF", "initView: viewModel.items.collectLatest PAGE = ${viewModel.page}")
                adapter.submitData(it)

                Log.d("WLDN HF", "PAGE = ${viewModel.page}")
//                if(viewModel.page == 0){
//                    Log.d("WLDN HF", "trigger viewModel.loadUserDataRemotely")
//                    viewModel.loadUserDataRemotely()
//                }
            }
        }

//        viewModel.triggerSomething()


        /*lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                viewModel.userList.collect { uiState ->
                    // New value received
                    when (uiState) {
                        is UiState.Success -> {
//                            adapter.refreshList(uiState.data)

                            lifecycleScope.launch {
                                viewModel.items.collectLatest {
                                    Log.d("WLDN HF", "initView: viewModel.items.collectLatest")
//                                    adapter.submitData(it)
                                }
                            }
                            stopLoadingIndicator()
                        }
                        is UiState.Error -> {
                            //TODO : handle error UI here
                            stopLoadingIndicator()
                        }
                        UiState.Loading -> {
                            if(viewModel.page == 1){
                                binding.srlUserList.isRefreshing = true
                            }
                            //TODO : handle Loading UI here
                        }
                    }
                }
            }
        }*/

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
                    Log.d("WLDN HF", "RV lastVisible = $lastVisible | currentListSize = $currentListSize | adapter.itemCount = ${adapter.itemCount}")

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
            binding.etSearch.requestFocus()
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchUser(binding.etSearch.text.toString())
                binding.etSearch.clearFocus() // Remove focus
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0) // Hide keyboard
                true
            } else {
                false
            }
        }

    }


    private fun searchUser(userName: String) {
        Log.d("WLDN HF", "searchUser: $userName")
    }

    private fun stopLoadingIndicator() {
        if (binding.srlUserList.isRefreshing) {
            binding.srlUserList.isRefreshing = false
        }
    }
}