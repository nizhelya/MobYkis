package com.yuzhny.mykis.presentation.appartment.family

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.family.request.GetFamilyFromFlat
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FamilyListViewModel @Inject constructor(
    private val getFamilyFromFlat: GetFamilyFromFlat
) : BaseViewModel(){
    private val _family = MutableLiveData<List<FamilyEntity>>()
    val family : LiveData<List<FamilyEntity>> = _family

    fun getFamily(addressId:Int , needFetch: Boolean = false) {
        getFamilyFromFlat(BooleanInt(addressId = addressId , needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleFamily(
                    it, !needFetch ,addressId
                )
            }
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
        getFamilyFromFlat.unsubscribe()
    }



}