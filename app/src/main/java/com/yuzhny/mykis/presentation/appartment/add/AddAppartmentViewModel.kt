package com.yuzhny.mykis.presentation.appartment.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.address.GetBlocks
import com.yuzhny.mykis.domain.address.GetStreetsFromBlock
import com.yuzhny.mykis.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.SplittableRandom
import javax.inject.Inject

@HiltViewModel
class AddAppartmentViewModel @Inject constructor(
    private val getBlocks: GetBlocks,
    private val getStreetsFromBlock: GetStreetsFromBlock
):BaseViewModel() {



    private val _address = MutableLiveData<List<AddressEntity>>()
    val address : LiveData<List<AddressEntity>> = _address

    private val _streets = MutableLiveData<List<AddressEntity>>()
    val streets: LiveData<List<AddressEntity>> = _streets

    fun getBlocksList(){
        getBlocks(true){ it ->
            it.either(::handleFailure) {
                handleAddress(
                    it
                )
            }
        }
    }

    fun getStreetList(blockId:Int){
        getStreetsFromBlock(blockId){ it ->
            it.either(::handleFailure) {
                handleStreets(
                    it
                )
            }
        }
    }

    private fun handleAddress(address:List<AddressEntity>){
        _address.value = address
    }
    private fun handleStreets(address:List<AddressEntity>){
        _streets.value = address
    }
}