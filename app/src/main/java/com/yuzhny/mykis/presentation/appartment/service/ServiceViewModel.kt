package com.yuzhny.mykis.presentation.appartment.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yuzhny.mykis.data.cache.service.ServiceCacheImpl
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.family.request.FamilyBooleanInt
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.service.request.ServiceParams
import com.yuzhny.mykis.domain.service.request.getFlatService
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val getFlatServiceUseCase: getFlatService ,
    private val serviceCacheImpl: ServiceCacheImpl
):BaseViewModel(){

    private val _servicesFlat = MutableLiveData<List<ServiceEntity>>()
    val servicesFlat : LiveData<List<ServiceEntity>> get() = _servicesFlat

    private val _totalDebt = MutableLiveData<ServiceEntity>()
    val totalDebt : LiveData<ServiceEntity> get() = _totalDebt

    fun getFlatService(addressId: Int , houseId: Int , service:Byte ,total:Byte ,qty:Byte , needFetch:Boolean = false) {
        getFlatServiceUseCase(ServiceParams(
            addressId = addressId ,
            houseId = houseId ,
            service = service,
            total = total,
            qty = qty,
            needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleService(
                    it, addressId , houseId , service , total , qty , !needFetch
                )
            }
        }
    }
    private fun handleService(services: List<ServiceEntity>,
                              addressId: Int,
                              houseId: Int,
                              service:Byte,
                              total:Byte,
                              qty:Byte,
                              fromCache: Boolean,
                              ) {
        _servicesFlat.value = services
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getFlatService(addressId, houseId , service,total, qty, true)
        }
    }
    fun getDebtFromCache(addressId: Int){
        viewModelScope.launch {
            _totalDebt.value = serviceCacheImpl.getTotalDebt(addressId)
        }
    }
}