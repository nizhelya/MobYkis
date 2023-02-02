package com.yuzhny.mykis.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuzhny.mykis.data.cache.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppartmentListViewModel @Inject constructor(private val repository: AppartmentRepository):ViewModel() {

    fun insertAppartment(appartment:AppartmentEntity){
        viewModelScope.launch {
            repository.addAppartment(appartment)
        }
    }

}