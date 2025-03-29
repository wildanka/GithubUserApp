package com.example.hcsgithubuser.search.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.hcsgithubuser.base.presentation.BaseBottomSheetDialogFragment
import com.example.hcsgithubuser.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseBottomSheetDialogFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModel()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)

    override fun initView() {
        expandFull(binding.layout)
        binding.etSearch.doOnTextChanged { text, start, before, count ->
            Log.d("WLDN SF", "onViewCreated: $text")
            viewModel.setQuery(text.toString())
        }

        lifecycleScope.launch {
            viewModel.query.collectLatest { query ->
                Log.d("WLDN SF Debounce", "Final query: $query")
            }
        }

    }


}