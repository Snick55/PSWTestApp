package com.github.snick55.pswtestapp.presentation.carsList

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.snick55.pswtestapp.R
import com.github.snick55.pswtestapp.core.navigateTo
import com.github.snick55.pswtestapp.core.observe
import com.github.snick55.pswtestapp.core.viewBinding
import com.github.snick55.pswtestapp.databinding.FragmentCarlistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCarsList: Fragment(R.layout.fragment_carlist) {

    private val binding by viewBinding<FragmentCarlistBinding>()
    private val viewModel by viewModels<CarsListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val carsAdapter = CarsListAdapter(onItemPressed = {
            val direction = FragmentCarsListDirections.actionFragmentCarsListToFragmentDetails(it)
            navigateTo(direction)
        })
        with(binding.carsRecycler){
            layoutManager = LinearLayoutManager(requireContext())
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
            adapter= carsAdapter
        }

        binding.sortCheckBox.setOnClickListener {
            viewModel.getCars((it as CheckBox).isChecked)
        }

        binding.addCarButton.setOnClickListener {
            val directions = FragmentCarsListDirections.actionFragmentCarsListToFragmentAddCar()
            navigateTo(directions)
        }
        binding.root.observe(viewLifecycleOwner,viewModel.cars){
            carsAdapter.setList(it)
            binding.carsRecycler.scrollToPosition(0)
        }

        binding.root.setTryAgainListener {
            viewModel.getCars(binding.sortCheckBox.isChecked)
        }

        binding.filtersButton.setOnClickListener {
            val directions = FragmentCarsListDirections.actionFragmentCarsListToFragmentFilters2()
            navigateTo(directions)
        }

    }


    override fun onResume() {
        super.onResume()
        viewModel.getCars(binding.sortCheckBox.isChecked)
    }
}




