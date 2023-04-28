package com.yuzhny.mykis.presentation.appartment.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yuzhny.mykis.data.cache.service.ServiceCacheImpl
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.service.request.ServiceParams
import com.yuzhny.mykis.domain.service.request.getFlatService
import com.yuzhny.mykis.domain.service.request.getTotalDebtService
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.BufferedOutputStream
import java.security.Provider.Service
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val getFlatServiceUseCase: getFlatService ,
    private val getTotalDebtServiceUseCase : getTotalDebtService
):BaseViewModel(){

    private val _servicesFlat = MutableLiveData<List<ServiceEntity>>()
    val servicesFlat : LiveData<List<ServiceEntity>> get() = _servicesFlat


    private val _serviceDetail = MutableLiveData<List<ServiceEntity>>()
    val serviceDetail : LiveData<List<ServiceEntity>> get() = _serviceDetail

    private val _totalDebt = MutableLiveData<ServiceEntity?>()
    val totalDebt : LiveData<ServiceEntity?> get() = _totalDebt

    val totalPay = MutableLiveData<Double>()

    var currentService :Byte = 0
    var currentServiceTitle :String = ""

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
    fun getDetailService(addressId: Int , houseId: Int , service:Byte ,total:Byte ,qty:Byte , needFetch:Boolean = false) {
        getFlatServiceUseCase(ServiceParams(
            addressId = addressId ,
            houseId = houseId ,
            service = service,
            total = total,
            qty = qty,
            needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleDetailService(
                    it,addressId , houseId , service , total , qty , !needFetch
                )
            }
        }
    }
    private fun handleDetailService(services: List<ServiceEntity>,
                              addressId: Int,
                              houseId: Int,
                              service:Byte,
                              total:Byte,
                              qty:Byte,
                              fromCache: Boolean,
    ) {
        _serviceDetail.value = services
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getDetailService(addressId, houseId , service,total, qty, true)
        }
    }
    fun getTotalService(addressId: Int){
        getTotalDebtServiceUseCase(addressId) { it ->
            it.either(::handleFailure) {
                handle(
                    it,_totalDebt
                )
            }
        }
    }
    private fun handle(address: ServiceEntity?, liveData : MutableLiveData<ServiceEntity?> ){
        liveData.value = address
    }
    fun clearTotal(){
        _totalDebt.value = null
    }
    fun clearService(){
        _serviceDetail.value = listOf()
    }
}