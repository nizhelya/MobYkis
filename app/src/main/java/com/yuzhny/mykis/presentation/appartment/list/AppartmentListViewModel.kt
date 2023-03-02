package com.yuzhny.mykis.presentation.appartment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.request.GetAppartments
import com.yuzhny.mykis.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppartmentListViewModel @Inject constructor(
    private val repository: AppartmentCache,
    private val getAppartmentsUseCase: GetAppartments
) : BaseViewModel() {

    private val _appartment = MutableLiveData<AppartmentEntity>()
    val appartment: LiveData<AppartmentEntity> get() = _appartment

    private val _appartments = MutableLiveData<List<AppartmentEntity>>()
    val appartments: LiveData<List<AppartmentEntity>> get() = _appartments

    private val _address = MutableLiveData<List<AddressEntity>>()
    val address: LiveData<List<AddressEntity>> get() = _address

    var app = AppartmentEntity()

    fun getAppartmentsByUser(needFetch: Boolean = false) {
        getAppartmentsUseCase(needFetch) { it ->
            it.either(::handleFailure) {
                handleAppartments(
                    it, !needFetch
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


    override fun onCleared() {
        super.onCleared()
        getAppartmentsUseCase.unsubscribe()
    }
}