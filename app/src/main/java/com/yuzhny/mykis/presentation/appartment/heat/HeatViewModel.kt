package com.yuzhny.mykis.presentation.appartment.heat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.heat.meter.request.GetHeatMeter
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeatViewModel @Inject constructor(
    private val getHeatMeterUseCase : GetHeatMeter
) :BaseViewModel(){
    private val _heatMeters = MutableLiveData<List<HeatMeterEntity>>()
    val heatMeters: LiveData<List<HeatMeterEntity>> get() = _heatMeters

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
}