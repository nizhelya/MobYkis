package com.yuzhny.mykis.presentation.appartment.add

import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.address.GetStreetsFromBlock
import com.yuzhny.mykis.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestStreetViewModel @Inject constructor(private val getStreetsFromBlock :GetStreetsFromBlock) : BaseViewModel() {

    val street = MutableLiveData<List<AddressEntity>>()

    fun getStreetsList(blockId:Int){
        getStreetsFromBlock(blockId){
                it -> it.either(::handleFailure){
            handleStreet(
                it
            )
        }
        }
    }
    private fun handleStreet(address:List<AddressEntity>){
        street.value = address
    }
}