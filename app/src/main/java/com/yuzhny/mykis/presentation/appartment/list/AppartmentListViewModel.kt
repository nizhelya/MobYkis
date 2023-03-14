package com.yuzhny.mykis.presentation.appartment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.data.cache.appartment.AppartmentCacheImpl
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.request.DeleteFlatByUser
import com.yuzhny.mykis.domain.appartment.request.GetAppartments
import com.yuzhny.mykis.domain.appartment.request.GetFlatById
import com.yuzhny.mykis.domain.appartment.request.UpdateBti
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppartmentListViewModel @Inject constructor(
    private val getAppartmentsUseCase: GetAppartments,
    private val deleteFlatByUser: DeleteFlatByUser,
    private val updateBtiUseCase:UpdateBti ,
    private val getFlatByIdUseCase : GetFlatById ,
    private val appartmentCacheImpl: AppartmentCacheImpl
) : BaseViewModel() {

    private val _appartment = MutableLiveData<AppartmentEntity>()
    val appartment: LiveData<AppartmentEntity> get() = _appartment

    private val _appartments = MutableLiveData<List<AppartmentEntity>>()
    val appartments: LiveData<List<AppartmentEntity>> get() = _appartments

    private val _address = MutableLiveData<List<AddressEntity>>()
    val address: LiveData<List<AddressEntity>> get() = _address

    private val _resultText = MutableLiveData<GetSimpleResponse>()
    val resultText: LiveData<GetSimpleResponse> = _resultText

    var currentAddress:Int = 0
    var currentHouse:Int = 0
    fun deleteFlat(addressId:Int){
        deleteFlatByUser(addressId){
            it -> it.either(::handleFailure){
                    handleResultText(
                        it , _resultText
                    )
            }
        }
    }

    fun getAppartmentsByUser(needFetch: Boolean = false) {
        getAppartmentsUseCase(needFetch) { it ->
            it.either(::handleFailure) {
                handleAppartments(
                    it, !needFetch
                )
            }
        }
    }
    fun getFlatFromCache(addressId: Int){
        viewModelScope.launch {
            _appartment.value = appartmentCacheImpl.getAppartmentById(addressId)
        }
    }
    fun updateBti(addressId:Int , phone:String , email:String){
        updateBtiUseCase(AppartmentEntity(addressId = addressId , phone = phone , email = email)){
                it -> it.either(::handleFailure){
            handleResultText(
                it , _resultText
                )
            }
        }
    }
    fun getAppartment(appartmentEntity: AppartmentEntity){
        _appartment.value = appartmentEntity
    }
    private fun handleAppartments(appartments: List<AppartmentEntity>, fromCache: Boolean) {
        _appartments.value = appartments
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getAppartmentsByUser(true)
        }
    }

    private fun handleResultText(result: GetSimpleResponse, liveData: MutableLiveData<GetSimpleResponse>){
        liveData.value = result
    }

    override fun onCleared() {
        super.onCleared()
        getAppartmentsUseCase.unsubscribe()
        deleteFlatByUser.unsubscribe()
    }
}