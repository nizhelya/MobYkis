package com.yuzhny.mykis.presentation.appartment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.address.GetBlocks
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.GetAppartments
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.family.request.FamilyBooleanInt
import com.yuzhny.mykis.domain.family.request.GetFamilyFromFlat
import com.yuzhny.mykis.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppartmentListViewModel @Inject constructor(
    private val repository: AppartmentCache,
    private val getAppartmentsUseCase: GetAppartments,
    private val getFamilyFromFlat: GetFamilyFromFlat
) : BaseViewModel() {

    private val _appartment = MutableLiveData<List<AppartmentEntity>>()
    val appartment: LiveData<List<AppartmentEntity>> get() = _appartment

    private val _address = MutableLiveData<List<AddressEntity>>()
    val address: LiveData<List<AddressEntity>> get() = _address

    private val _family = MutableLiveData<List<FamilyEntity>>()
    val family: LiveData<List<FamilyEntity>> get() = _family

    fun getAppartmentsByUser(needFetch: Boolean = false) {
        getAppartmentsUseCase(needFetch) { it ->
            it.either(::handleFailure) {
                handleAppartments(
                    it, !needFetch
                )
            }
        }
    }
    fun getFamily(addressId:Int , needFetch: Boolean = false) {
        getFamilyFromFlat(FamilyBooleanInt(addressId = addressId , needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleFamily(
                    it, !needFetch ,addressId
                )
            }
        }
    }


    private fun handleAppartments(appartments: List<AppartmentEntity>, fromCache: Boolean) {
        _appartment.value = appartments
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getAppartmentsByUser(true)
        }
    }
    private fun handleFamily(families: List<FamilyEntity>, fromCache: Boolean , addressId: Int) {
        _family.value = families
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getFamily(addressId, true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        getAppartmentsUseCase.unsubscribe()
    }
}