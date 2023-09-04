package com.github.snick55.pswtestapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.snick55.pswtestapp.domain.InflateTableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preference: PreferenceSource,
    private val useCase: InflateTableUseCase
) : ViewModel() {


    fun isFirstRun(): Boolean = preference.isFirstRun()


    fun setFlag(flag: Boolean) = preference.setFlag(false)

    fun inflateTable() = viewModelScope.launch(Dispatchers.IO) {
        useCase.inflate()
    }

}