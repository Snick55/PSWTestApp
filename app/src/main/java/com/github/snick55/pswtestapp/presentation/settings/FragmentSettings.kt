package com.github.snick55.pswtestapp.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.snick55.pswtestapp.R
import com.github.snick55.pswtestapp.core.Container
import com.github.snick55.pswtestapp.core.goBack
import com.github.snick55.pswtestapp.core.viewBinding
import com.github.snick55.pswtestapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSettings : Fragment(R.layout.fragment_settings) {


    private val args: FragmentSettingsArgs by navArgs()
    private val viewModel by viewModels<SettingsViewModel>()
    private val binding by viewBinding<FragmentSettingsBinding>()
    private var currentCar: CarSettingsUi? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCarById(args.carId)
        binding.custom.container = Container.Success("")
        viewModel.observeCar(viewLifecycleOwner) {
            currentCar = it
                with(binding) {
                it.show(brandEditText, descriptionEditText, manufactureEditText, priceEditText)
            }
        }
        binding.saveButton.setOnClickListener {
            viewModel.updateCar(simpleValidate())
            goBack()
        }
    }


    private fun simpleValidate(): CarSettingsUi {
        val brand = binding.brandEditText.text.toString().ifBlank { currentCar!!.brand }
        val description = binding.descriptionEditText.text.toString().ifBlank { currentCar!!.description }
        val manufactures = binding.manufactureEditText.text.toString().ifBlank { currentCar!!.manufacturer }
        val price = if (binding.priceEditText.text.toString()
                .isBlank()
        ) currentCar!!.price else binding.priceEditText.text.toString().toInt()
        return  CarSettingsUi(args.carId,brand,description,manufactures,price)
    }

}