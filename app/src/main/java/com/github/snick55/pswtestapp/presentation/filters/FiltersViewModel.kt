package com.github.snick55.pswtestapp.presentation.filters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.snick55.pswtestapp.core.Container
import com.github.snick55.pswtestapp.domain.FiltersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    private val useCase: FiltersUseCase
) : ViewModel() {

    private val _allFilters = MutableLiveData<Container<List<String>>>()
    val allFilters: LiveData<Container<List<String>>> = _allFilters


       fun getAllFilters() = viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllFilters().collect {
               withContext(Dispatchers.Main){
                    _allFilters.value = it
                }
            }
        }

}