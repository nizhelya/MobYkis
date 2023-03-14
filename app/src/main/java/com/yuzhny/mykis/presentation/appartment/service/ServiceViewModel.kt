package com.yuzhny.mykis.presentation.appartment.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.family.request.FamilyBooleanInt
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.service.request.ServiceParams
import com.yuzhny.mykis.domain.service.request.getFlatService
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val getFlatServiceUseCase: getFlatService
):BaseViewModel(){

    private val _serviceFlat = MutableLiveData<List<ServiceEntity>>()
    val serviceFlat : LiveData<List<ServiceEntity>> = _serviceFlat

    fun getFlatService(addressId:Int , houseId :Int , service:Byte , qty:Byte , needFetch: Boolean = false) {
        getFlatServiceUseCase(ServiceParams(
            addressId = addressId ,
            houseId = houseId ,
            service = service,
            qty = qty,
            needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleService(
                    it, addressId , houseId ,service ,qty , !needFetch
                )
            }
        }
    }
    private fun handleService(services: List<ServiceEntity>,
                              addressId: Int,
                              houseId: Int,
                              service:Byte,
                              qty:Byte,
                              fromCache: Boolean,
                              ) {
        _serviceFlat.value = services
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getFlatService(addressId, houseId , service, qty, true)
        }
    }
}