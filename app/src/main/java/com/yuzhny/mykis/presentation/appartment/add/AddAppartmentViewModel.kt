package com.yuzhny.mykis.presentation.appartment.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.domain.address.*
import com.yuzhny.mykis.domain.address.request.*
import com.yuzhny.mykis.domain.type.None
import com.yuzhny.mykis.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAppartmentViewModel @Inject constructor(
    private val getBlocks: GetBlocks,
    private val getStreetsFromBlock: GetStreetsFromBlock,
    private val getHousesFromStreet: GetHousesFromStreet,
    private val getFlatsFromHouse: GetFlatsFromHouse,
    private val addFlatByUser: AddFlatByUser ,
    private val checkCodeUseCase : CheckCode ,
    ):BaseViewModel() {



    private val _blocks = MutableLiveData<List<AddressEntity>>()
    val blocks : LiveData<List<AddressEntity>> = _blocks

    private val _streets = MutableLiveData<List<AddressEntity>>()
    val streets: LiveData<List<AddressEntity>> = _streets

    private val _houses = MutableLiveData<List<AddressEntity>>()
    val houses: LiveData<List<AddressEntity>> = _houses

    private val _flats = MutableLiveData<List<AddressEntity>>()
    val flats: LiveData<List<AddressEntity>> = _flats

    private val _resultText = MutableLiveData<GetAddressResponse>()
    val resultText: LiveData<GetAddressResponse> = _resultText

    private val _checkText = MutableLiveData<GetAddressResponse>()
    val checkText: LiveData<GetAddressResponse> = _checkText




    fun getBlocksList(){
        getBlocks(None()){ it ->
            it.either(::handleFailure) {
                handle(
                    it,
                    _blocks
                )
            }
        }
    }
    fun checkCode(kod : String , addressId: Int){
        checkCodeUseCase(FlatCode(kod = kod , addressId = addressId)){ it ->
            it.either(::handleFailure) {
                handleResultText(
                    it,
                    _checkText
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
    fun addFlat(addressId:Int){
        addFlatByUser(addressId){ it->
            it.either(::handleFailure){

                handleResultText(
                    it,
                    _resultText
                )
            }
        }
    }
    private fun handle(address: List<AddressEntity> , liveData : MutableLiveData<List<AddressEntity>> ){
            liveData.value = address
    }
    private fun handleResultText(result:GetAddressResponse , liveData: MutableLiveData<GetAddressResponse>){
        liveData.value = result
    }
    fun clearLiveData(){
        _houses.value = listOf()
        _flats.value =listOf()
    }
    fun clearAllLiveData(){
        _houses.value = listOf()
        _streets.value = listOf()
        _flats.value =listOf()
    }

    override fun onCleared() {
        super.onCleared()
        getBlocks.unsubscribe()
        getStreetsFromBlock.unsubscribe()
        getHousesFromStreet.unsubscribe()
        getFlatsFromHouse.unsubscribe()
        addFlatByUser.unsubscribe()
        checkCodeUseCase.unsubscribe()
    }

}