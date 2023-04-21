package com.yuzhny.mykis.presentation.appartment.water

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import com.yuzhny.mykis.domain.water.meter.request.GetWaterMeter
import com.yuzhny.mykis.domain.water.reading.request.AddNewWaterReading
import com.yuzhny.mykis.domain.water.reading.request.AddReadingParams
import com.yuzhny.mykis.domain.water.reading.request.DeleteCurrentWaterReading
import com.yuzhny.mykis.domain.water.reading.request.GetWaterReading
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject


@HiltViewModel
class WaterViewModel @Inject constructor(
    private val getWaterMeterUseCase: GetWaterMeter,
    private val getWaterReadingUseCase: GetWaterReading,
    private val addNewWaterReadingUseCase: AddNewWaterReading,
    private val deleteCurrentWaterReadingUseCase: DeleteCurrentWaterReading
) : BaseViewModel() {

    private val _waterMeters = MutableLiveData<List<WaterMeterEntity>>()
    val waterMeters: LiveData<List<WaterMeterEntity>> get() = _waterMeters

    private val _waterMeter = MutableLiveData<WaterMeterEntity>()
    val waterMeter: LiveData<WaterMeterEntity> get() = _waterMeter

    private val _waterReadings = MutableLiveData<List<WaterReadingEntity>>()
    val waterReadings: LiveData<List<WaterReadingEntity>> get() = _waterReadings

    private val _waterReading = MutableLiveData<WaterReadingEntity>()
    val waterReading: LiveData<WaterReadingEntity> get() = _waterReading

    private val _resultText = MutableLiveData<GetSimpleResponse>()
    val resultText: LiveData<GetSimpleResponse> = _resultText

    var currentReading: Int = 0
    var currentVodomerId: Int = 0
    var pokId: Int = 0
    var currentDateReading:String = ""
    fun getWaterMeters(addressId: Int, needFetch: Boolean = false) {
        getWaterMeterUseCase(BooleanInt(int = addressId, needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleWaterMeter(
                    it, !needFetch, addressId
                )
            }
        }
    }

    private fun handleWaterMeter(
        waterMeters: List<WaterMeterEntity>,
        fromCache: Boolean,
        addressId: Int
    ) {
        _waterMeters.value = waterMeters
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getWaterMeters(addressId, true)
        }
    }

    fun getWaterReadings(vodomerId: Int, needFetch: Boolean = false) {
        getWaterReadingUseCase(BooleanInt(int = vodomerId, needFetch = needFetch)) { it ->
            it.either(::handleFailure) {
                handleWaterReading(
                    it, !needFetch, vodomerId
                )
            }
        }
    }

    private fun handleWaterReading(
        waterReadings: List<WaterReadingEntity>,
        fromCache: Boolean,
        vodomerId: Int
    ) {
        _waterReadings.value = waterReadings
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getWaterReadings(vodomerId, true)
        }
    }

    fun getWaterMeter(waterMeter: WaterMeterEntity) {
        _waterMeter.value = waterMeter
    }

    fun getWaterReading(waterReading: WaterReadingEntity) {
        _waterReading.value = waterReading
        currentReading = waterReading.currant
        pokId = waterReading.pokId
        currentDateReading = waterReading.dateDo
    }

    private fun handleResultText(
        result: GetSimpleResponse,
        liveData: MutableLiveData<GetSimpleResponse>
    ) {
        liveData.value = result
    }

    fun addNewWaterReading(newValue: Int) {
        addNewWaterReadingUseCase(
            AddReadingParams(
                currentVodomerId,
                newValue,
                currentReading
            )
        ) { it ->
            it.either(::handleFailure) {
                handleResultText(
                    it, _resultText
                )
            }
        }
    }

    fun deleteCurrentWaterReading() {
        deleteCurrentWaterReadingUseCase(pokId) { it ->
            it.either(::handleFailure) {
                handleResultText(
                    it, _resultText
                )
            }
        }
    }

}