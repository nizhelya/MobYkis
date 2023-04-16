package com.yuzhny.mykis.presentation.appartment.heat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.databinding.ItemHeatMeterBinding
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import com.yuzhny.mykis.presentation.appartment.water.WaterMeterShortListener
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class HeatMeterListAdapter @Inject constructor(val heatMeterShortListener: HeatMeterShortListener) :
    ListAdapter<HeatMeterEntity, HeatMeterListAdapter.HeatMeterViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeatMeterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeatMeterListAdapter.HeatMeterViewHolder(
            ItemHeatMeterBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HeatMeterViewHolder, position: Int) {
        val meter = getItem(position)
        holder.binding.apply {
            number.text = meter.number
            heatMeterText.text = meter.model
            if(trueOrFalse(meter.spisan) || trueOrFalse(meter.out) ){
                firstLayout.alpha = .5f
                if(meter.spisan==1.toByte()){
                    dopText.text = "Списан"
                }else {
                    dopText.text = "На перевірці"
                }
            }else{
                dopText.text = "Працює"
            }
            data = meter
            clickShortListener = heatMeterShortListener
        }
    }




    class HeatMeterViewHolder(var binding: ItemHeatMeterBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object DiffCallback : DiffUtil.ItemCallback<HeatMeterEntity>() {
        override fun areItemsTheSame(oldItem: HeatMeterEntity, newItem: HeatMeterEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HeatMeterEntity, newItem: HeatMeterEntity): Boolean {
            return oldItem == newItem
        }

    }


}
class HeatMeterShortListener @Inject constructor(){

    var onItemClick: ((HeatMeterEntity) -> Unit)? = null

    fun onClick(data: HeatMeterEntity) {
        onItemClick?.invoke(data)
    }


}