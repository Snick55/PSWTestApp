package com.github.snick55.pswtestapp.presentation.addCar

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.snick55.pswtestapp.R
import com.github.snick55.pswtestapp.domain.EmptyFieldException
import com.github.snick55.pswtestapp.domain.Field
import com.github.snick55.pswtestapp.domain.IllegalPriceException
import java.lang.Exception
import javax.inject.Inject

interface AddCommunication {

    fun map(e: Exception)

    fun observe(owner: LifecycleOwner, observer: Observer<State>)

    fun showProgress()

    fun hideProgress()

    fun canGoBack()


    class Base @Inject constructor() : AddCommunication {


        private val state = MutableLiveData(State())


        override fun map(e: Exception) {
            when (e) {
                is IllegalPriceException -> handleIllegalPriceError()
                is EmptyFieldException -> handleEmptyFieldError(e)
            }
        }

        override fun canGoBack() {
            state.value = state.value?.copy(canGoBack = true)
        }

        private fun handleIllegalPriceError() {
            state.value = state.value?.copy(priceErrorMessageRes = R.string.illegal_price)
        }

        override fun observe(
            owner: LifecycleOwner,
            observer: Observer<State>
        ) {
            state.observe(owner, observer)
        }

        override fun showProgress() {
            state.value = State(CreatingInProgress = true)
        }

        override fun hideProgress() {
            state.value = state.value?.copy(CreatingInProgress = false)
        }

        private fun handleEmptyFieldError(e: EmptyFieldException) {
            state.value = when (e.field) {
                Field.BRAND -> state.value
                    ?.copy(brandErrorMessageRes = R.string.field_is_empty)
                Field.MANUFACTURES -> state.value
                    ?.copy(manufacturerErrorMessageRes = R.string.field_is_empty)
                Field.PRICE -> state.value
                    ?.copy(priceErrorMessageRes = R.string.field_is_empty)
            }
            hideProgress()
        }
    }


    data class State(
        @StringRes val brandErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val manufacturerErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val priceErrorMessageRes: Int = NO_ERROR_MESSAGE,
        private val CreatingInProgress: Boolean = false,
        var canGoBack: Boolean = false
    ) {
        val showProgress: Boolean get() = CreatingInProgress
        val enableViews: Boolean get() = !CreatingInProgress
    }


    companion object {
        const val NO_ERROR_MESSAGE = 0
    }

}