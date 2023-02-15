package com.yuzhny.mykis.presentation.appartment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.domain.address.GetBlocks
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.GetAppartments
import com.yuzhny.mykis.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppartmentListViewModel @Inject constructor(
    private val repository: AppartmentCache,
    private val getAppartmentsUseCase: GetAppartments,
    private val getBlocks: GetBlocks
) : BaseViewModel() {

    private val _appartment = MutableLiveData<List<AppartmentEntity>>()
    val appartment: LiveData<List<AppartmentEntity>> get() = _appartment


    //    fun getAppartments(needFetch: Boolean = false) {
//        getAppartmentsUseCase(needFetch) { it ->
//            it.either(::handleFailure) {
//                handleAppartments(
//                    it,
//                    !needFetch
//                )
//            }
//        }
//    }
    fun getAppartmentsByUser(needFetch: Boolean = false) {
        getAppartmentsUseCase(needFetch) { it ->
            it.either(::handleFailure) {
                handleAppartments(
                   it, !needFetch
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

    fun testBlocks() {
        getBlocks(true){
            it -> it.either(::handleFailure)
        }
    }

//    fun insertAppartment(appart:List<AppartmentEntity>){
//        viewModelScope.launch {
//            repository.addAppartment(appart)
//        }
//    }
//
//
//    suspend fun getAppartmentById(addressId:Int):List<AppartmentEntity>{
//            try {
//               return  repository.remoteGetAppartments(addressId)
//            } catch ( e: Exception) {
//                Log.d("TestRemote", "$e")
//                return listOf()
//            }
//        }
//    fun saveAppartmentById(addressId: Int){
//        viewModelScope.launch {
//            repository.addAppartment(getAppartmentById(addressId))
//        }
//    }

}