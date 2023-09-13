package com.github.snick55.pswtestapp.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.snick55.pswtestapp.R
import com.github.snick55.pswtestapp.core.navigateTo
import com.github.snick55.pswtestapp.core.viewBinding
import com.github.snick55.pswtestapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetails: Fragment(R.layout.fragment_details) {

    private val args: FragmentDetailsArgs by navArgs()
    private val viewModel by viewModels<DetailsViewModel>()
    private val binding by viewBinding<FragmentDetailsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCarById(args.carId)
        viewModel.observeDetailCar(viewLifecycleOwner){
            with(binding){
                it.show(brandTextView,manufactureTextView,priceTextView,descriptionTextView)
            }
        }

        binding.settingButton.setOnClickListener {
            val directions = FragmentDetailsDirections.actionFragmentDetailsToFragmentSettings(args.carId)
            navigateTo(directions)
        }

    }

}