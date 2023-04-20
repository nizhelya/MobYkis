package com.yuzhny.mykis.presentation.appartment.water

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.databinding.ItemWaterMeterBinding
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
@FragmentScoped
class WaterMeterListAdapter @Inject constructor(val waterMeterShortListener: WaterMeterShortListener) :ListAdapter<WaterMeterEntity,WaterMeterListAdapter.WaterMeterViewHolder >(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterMeterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WaterMeterListAdapter.WaterMeterViewHolder(
            ItemWaterMeterBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WaterMeterViewHolder, position: Int) {
        val meter = getItem(position)
        holder.binding.apply {
            waterMeterText.text = meter.model
            if(trueOrFalse(meter.spisan) || trueOrFalse(meter.out) || trueOrFalse(meter.paused)){
                firstLayout.alpha = .5f
                if(meter.spisan==1.toByte()){
                    dopText.text = "Списан"
                }else if(meter  .out == 1.toByte()){
                    dopText.text = "На перевірці"
                }else {
                    dopText.text = "Призупинен"
                }
            }else{
                dopText.text = "Працює"
            }
            typeVoda.text = meter.voda
            number.text = meter.nomer
            place.text = meter.place
            data = meter
            clickShortListener = waterMeterShortListener

        }
    }




    class WaterMeterViewHolder(var binding: ItemWaterMeterBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object DiffCallback : DiffUtil.ItemCallback<WaterMeterEntity>() {
        override fun areItemsTheSame(oldItem: WaterMeterEntity, newItem: WaterMeterEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WaterMeterEntity, newItem: WaterMeterEntity): Boolean {
            return oldItem == newItem
        }

    }


}
class WaterMeterShortListener @Inject constructor(){

    var onItemClick: ((WaterMeterEntity) -> Unit)? = null

    fun onClick(data: WaterMeterEntity) {
        onItemClick?.invoke(data)
    }


}

