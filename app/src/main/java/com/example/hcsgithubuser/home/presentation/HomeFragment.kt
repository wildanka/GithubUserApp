package com.example.hcsgithubuser.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.hcsgithubuser.base.data.UiState
import com.example.hcsgithubuser.base.presentation.BaseFragment
import com.example.hcsgithubuser.databinding.FragmentHomeBinding
import com.example.hcsgithubuser.home.presentation.adapter.GithubUserListAdapter
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
        val adapter = GithubUserListAdapter()
        binding.rvUserList.adapter = adapter

        viewModel.userList
        viewModel.triggerSomething()
        lifecycleScope.launch {
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
                            adapter.refreshList(uiState.data)
                        }
                        is UiState.Error -> {
                            //TODO : handle error UI here
                        }
                        UiState.Loading -> {
                            //TODO : handle Loading UI here
                        }
                    }
                }
            }
        }

    }
}