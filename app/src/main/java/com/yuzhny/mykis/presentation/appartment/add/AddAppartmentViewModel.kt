package com.yuzhny.mykis.presentation.appartment.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.address.GetBlocks
import com.yuzhny.mykis.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.SplittableRandom
import javax.inject.Inject

@HiltViewModel
class AddAppartmentViewModel @Inject constructor(
    private val getBlocks: GetBlocks
):BaseViewModel() {

    private val _address = MutableLiveData<List<AddressEntity>>()
    val address : LiveData<List<AddressEntity>> = _address

    fun getBlocksList(){
        getBlocks(true){ it ->
            it.either(::handleFailure) {
                handleAddress(
                    it
                )
            }
        }
    }

    private fun handleAddress(address:List<AddressEntity>){
        _address.value = address
    }
}