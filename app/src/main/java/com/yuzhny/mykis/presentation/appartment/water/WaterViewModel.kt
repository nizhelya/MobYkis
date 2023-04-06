package com.yuzhny.mykis.presentation.appartment.water

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import com.yuzhny.mykis.domain.water.meter.request.GetWaterMeter
import com.yuzhny.mykis.domain.water.reading.request.GetWaterReading
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WaterViewModel @Inject constructor(
    private val getWaterMeterUseCase : GetWaterMeter,
    private val getWaterReadingUseCase : GetWaterReading
):BaseViewModel() {

    private val _waterMeters = MutableLiveData<List<WaterMeterEntity>>()
    val waterMeters :LiveData<List<WaterMeterEntity>> get() = _waterMeters

    private val _waterMeter = MutableLiveData<WaterMeterEntity>()
    val waterMeter :LiveData<WaterMeterEntity> get() = _waterMeter

    private val _waterReadings = MutableLiveData<List<WaterReadingEntity>>()
    val waterReadings :LiveData<List<WaterReadingEntity>> get() = _waterReadings

    var currentVodomerId:Int = 0
    fun getWaterMeters(addressId:Int, needFetch: Boolean = false) {
        getWaterMeterUseCase(BooleanInt(int = addressId , needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleWaterMeter(
                    it, !needFetch ,addressId
                )
            }
        }
    }
    private fun handleWaterMeter(waterMeters: List<WaterMeterEntity>, fromCache: Boolean, addressId: Int) {
        _waterMeters.value = waterMeters
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getWaterMeters(addressId, true)
        }
    }
    fun getWaterReadings(vodomerId:Int, needFetch: Boolean = false) {
        getWaterReadingUseCase(BooleanInt(int = vodomerId , needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleWaterReading(
                it, !needFetch ,vodomerId
                )
            }
        }
    }
    private fun handleWaterReading(waterReadings: List<WaterReadingEntity>, fromCache: Boolean, vodomerId: Int) {
        _waterReadings.value = waterReadings
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getWaterReadings(vodomerId, true)
        }
    }
    fun getWaterMeter(waterMeter: WaterMeterEntity){
        _waterMeter.value = waterMeter
    }
}