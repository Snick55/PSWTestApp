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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCarById(args.carId)

        viewModel.observeCar(viewLifecycleOwner) {
            with(binding) {
                it.show(brandEditText, descriptionEditText, manufactureEditText, priceEditText)
            }
        }

        binding.custom.container = Container.Success("")
        binding.saveButton.setOnClickListener {
            val price = if (binding.priceEditText.text.toString()
                    .isBlank()
            ) 0 else binding.priceEditText.text.toString().toInt()
            val carSettingsUi = CarSettingsUi(
                args.carId,
                binding.brandEditText.text.toString(),
                binding.descriptionEditText.text.toString(),
                binding.manufactureEditText.text.toString(),
                price
            )
            viewModel.updateCar(carSettingsUi)
            goBack()
        }
    }

}