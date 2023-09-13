package com.github.snick55.pswtestapp.presentation.filters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.snick55.pswtestapp.R
import com.github.snick55.pswtestapp.core.Logg
import com.github.snick55.pswtestapp.core.observe
import com.github.snick55.pswtestapp.core.viewBinding
import com.github.snick55.pswtestapp.databinding.FragmentFiltersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFilters: Fragment(R.layout.fragment_filters) {

    private val binding by viewBinding<FragmentFiltersBinding>()
    private val viewModel by viewModels<FiltersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllFilters()
        val filtersAdapter = FiltersAdapter()

        with(binding.filtersRecyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
            adapter= filtersAdapter
        }
        binding.root.observe(viewLifecycleOwner,viewModel.allFilters){
            filtersAdapter.setUpList(it)
        }
    }


}