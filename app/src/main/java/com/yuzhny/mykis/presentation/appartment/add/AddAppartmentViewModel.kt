package com.yuzhny.mykis.presentation.appartment.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.address.*
import com.yuzhny.mykis.domain.appartment.GetAppartments
import com.yuzhny.mykis.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.SplittableRandom
import javax.inject.Inject

@HiltViewModel
class AddAppartmentViewModel @Inject constructor(
    private val getBlocks: GetBlocks,
    private val getStreetsFromBlock: GetStreetsFromBlock,
    private val getHousesFromStreet: GetHousesFromStreet,
    private val getFlatsFromHouse: GetFlatsFromHouse,
    private val getAppartmentsUseCase: GetAppartments
    ):BaseViewModel() {



    private val _blocks = MutableLiveData<List<AddressEntity>>()
    val blocks : LiveData<List<AddressEntity>> = _blocks

    private val _streets = MutableLiveData<List<AddressEntity>>()
    val streets: LiveData<List<AddressEntity>> = _streets

    private val _houses = MutableLiveData<List<AddressEntity>>()
    val houses: LiveData<List<AddressEntity>> = _houses

    private val _flats = MutableLiveData<List<AddressEntity>>()
    val flats: LiveData<List<AddressEntity>> = _flats

    fun getBlocksList(){
        getBlocks(true){ it ->
            it.either(::handleFailure) {
                handle(
                    it,
                    _blocks
                )
            }
        }
    }

    fun getStreetList(blockId:Int){
        getStreetsFromBlock(blockId){ it ->
            it.either(::handleFailure) {
                handle(
                    it,
                    _streets
                )
            }
        }
    }
    fun getHousesList(streetId:Int, blockId:Int){
        getHousesFromStreet( AddressEntity( streetId = streetId , blockId = blockId)){ it ->
            it.either(::handleFailure) {
                handle(
                    it,
                    _houses
                )
            }
        }
    }
    fun getFlatsList(houseId:Int){
        getFlatsFromHouse(houseId){ it ->
            it.either(::handleFailure) {
                handle(
                    it,
                    _flats
                )
            }
        }
    }
    private fun handle(address: List<AddressEntity> , liveData : MutableLiveData<List<AddressEntity>> ){
            liveData.value = address
    }
}