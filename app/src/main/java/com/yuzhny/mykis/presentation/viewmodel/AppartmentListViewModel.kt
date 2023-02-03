package com.yuzhny.mykis.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuzhny.mykis.data.cache.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppartmentListViewModel @Inject constructor(private val repository: AppartmentRepository):ViewModel() {

    private val _appartment = MutableLiveData<List<AppartmentEntity>>()
    val appartment :LiveData<List<AppartmentEntity>> get() = _appartment


    fun insertAppartment(appart:List<AppartmentEntity>){
        viewModelScope.launch {
            repository.addAppartment(appart)
        }
    }


    suspend fun getAppartmentById(addressId:Int):List<AppartmentEntity>{
            try {
               return  repository.remoteGetAppartments(addressId)
            } catch ( e: Exception) {
                Log.d("TestRemote", "$e")
                return listOf()
            }
        }
    fun saveAppartmentById(addressId: Int){
        viewModelScope.launch {
            repository.addAppartment(getAppartmentById(addressId))
        }
    }

}