package com.yuzhny.mykis.presentation.appartment.heat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.heat.meter.request.GetHeatMeter
import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import com.yuzhny.mykis.domain.heat.reading.request.AddHeatReadingParams
import com.yuzhny.mykis.domain.heat.reading.request.AddNewHeatReading
import com.yuzhny.mykis.domain.heat.reading.request.DeleteCurrentHeatReading
import com.yuzhny.mykis.domain.heat.reading.request.GetHeatReading
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import com.yuzhny.mykis.domain.water.reading.request.AddReadingParams
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeatViewModel @Inject constructor(
    private val getHeatMeterUseCase : GetHeatMeter,
    private val getHeatReadingUseCase: GetHeatReading,
    private val addNewHeatReadingUseCase: AddNewHeatReading,
    private val deleteCurrentHeatReadingUseCase: DeleteCurrentHeatReading
) :BaseViewModel(){
    private val _heatMeters = MutableLiveData<List<HeatMeterEntity>>()
    val heatMeters: LiveData<List<HeatMeterEntity>> get() = _heatMeters

    private val _heatMeter = MutableLiveData<HeatMeterEntity>()
    val heatMeter : LiveData<HeatMeterEntity> get() = _heatMeter

    private val _heatReadings = MutableLiveData<List<HeatReadingEntity>>()
    val heatReadings :LiveData<List<HeatReadingEntity>> get() =  _heatReadings

    private val _heatReading = MutableLiveData<HeatReadingEntity>()
    val heatReading: LiveData<HeatReadingEntity> get() = _heatReading

    private val _resultText = MutableLiveData<GetSimpleResponse>()
    val resultText: LiveData<GetSimpleResponse> = _resultText

    var currentTeplomerId:Int = 0
    var pokId :Int = 0
    var currentReading : Double = 0.0
    fun getHeatMeters(addressId: Int, needFetch: Boolean = false) {
        getHeatMeterUseCase(BooleanInt(int = addressId, needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleWaterMeter(
                    it, !needFetch, addressId
                )
            }
        }
    }

    private fun handleWaterMeter(
        heatMeters: List<HeatMeterEntity>,
        fromCache: Boolean,
        addressId: Int
    ) {
        _heatMeters.value = heatMeters
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getHeatMeters(addressId, true)
        }
    }
    fun getHeatReadings( needFetch: Boolean = false) {
        getHeatReadingUseCase(BooleanInt(currentTeplomerId, needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleWaterReading(
                    it, !needFetch
                )
            }
        }
    }

    private fun handleWaterReading(
        heatReadings: List<HeatReadingEntity>,
        fromCache: Boolean,
    ) {
        _heatReadings.value = heatReadings
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getHeatReadings( true)
        }
    }
    fun getHeatMeter(heatMeter:HeatMeterEntity){
        _heatMeter.value = heatMeter
    }
    fun getHeatReading(heatReading: HeatReadingEntity) {
        _heatReading.value = heatReading
        currentReading = heatReading.currant
        pokId = heatReading.pokId
    }
    private fun handleResultText(
        result: GetSimpleResponse,
        liveData: MutableLiveData<GetSimpleResponse>
    ) {
        liveData.value = result
    }

    fun addNewHeatReading(newValue: Double) {
        addNewHeatReadingUseCase(
            AddHeatReadingParams(
                currentTeplomerId,
                newValue,
                currentReading
            )
        ) { it ->
            it.either(::handleFailure) {
                handleResultText(
                    it , _resultText
                )
            }
        }
    }
    fun deleteCurrentWaterReading() {
        deleteCurrentHeatReadingUseCase(pokId) { it ->
            it.either(::handleFailure) {
                handleResultText(
                    it, _resultText
                )
            }
        }
    }
}