package com.github.snick55.pswtestapp.presentation.addCar

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.snick55.pswtestapp.R
import com.github.snick55.pswtestapp.core.Logg
import com.github.snick55.pswtestapp.core.goBack
import com.github.snick55.pswtestapp.core.viewBinding
import com.github.snick55.pswtestapp.databinding.FragmentAddCarBinding
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAddCar : Fragment(R.layout.fragment_add_car) {

    private val binding by viewBinding<FragmentAddCarBinding>()
    private val viewModel by viewModels<AddCarViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createCarButton.setOnClickListener {
            val brand = binding.brandEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()
            val manufactures = binding.manufactureEditText.text.toString()
            val price = binding.priceEditText.text.toString()
            viewModel.createCar(
                CarAddUi(
                    brand = brand,
                    description = description,
                    manufacturer = manufactures,
                    price = price
                )
            )
        }

        viewModel.observeState(viewLifecycleOwner){state->
            binding.createCarButton.isEnabled = state.enableViews
            binding.brantInputLayout.isEnabled = state.enableViews
            binding.manufactureInputLayout.isEnabled = state.enableViews
            binding.priceInputLayout.isEnabled = state.enableViews
            binding.descriptionTextInput.isEnabled = state.enableViews

            fillError(binding.brantInputLayout, state.brandErrorMessageRes)
            fillError(binding.manufactureInputLayout, state.manufacturerErrorMessageRes)
            fillError(binding.priceInputLayout, state.priceErrorMessageRes)

            binding.progressBar.visibility = if (state.showProgress) View.VISIBLE else View.INVISIBLE

            if (state.canGoBack) {
                goBack()
            }
        }
    }

    private fun fillError(input: TextInputLayout, @StringRes errorMessageRes: Int) {

        if (errorMessageRes == AddCommunication.NO_ERROR_MESSAGE){
            input.error = null
            input.isErrorEnabled = false
        }else{
            input.error = getString(errorMessageRes)
            input.isErrorEnabled = true
        }
    }


}