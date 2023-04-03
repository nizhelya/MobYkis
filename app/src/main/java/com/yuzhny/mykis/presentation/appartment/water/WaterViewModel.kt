package com.yuzhny.mykis.presentation.appartment.water

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.water.WaterMeterEntity
import com.yuzhny.mykis.domain.water.request.GetWaterMeter
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WaterViewModel @Inject constructor(
    private val getWaterMeterUseCase : GetWaterMeter
):BaseViewModel() {

    private val _waterMeters = MutableLiveData<List<WaterMeterEntity>>()
    val waterMeters :LiveData<List<WaterMeterEntity>> get() = _waterMeters

    private val _waterMeter = MutableLiveData<WaterMeterEntity>()
    val waterMeter :LiveData<WaterMeterEntity> get() = _waterMeter
    var currentVodomerId:Int = 0
    fun getWaterMeters(addressId:Int, needFetch: Boolean = false) {
        getWaterMeterUseCase(BooleanInt(addressId = addressId , needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleFamily(
                    it, !needFetch ,addressId
                )
            }
        }
    }
    private fun handleFamily(waterMeters: List<WaterMeterEntity>, fromCache: Boolean, addressId: Int) {
        _waterMeters.value = waterMeters
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getWaterMeters(addressId, true)
        }
    }
    fun getWaterMeter(waterMeter:WaterMeterEntity){
        _waterMeter.value = waterMeter
    }
}