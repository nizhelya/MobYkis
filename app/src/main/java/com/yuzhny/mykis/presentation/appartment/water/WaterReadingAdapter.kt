package com.yuzhny.mykis.presentation.appartment.water

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.databinding.ItemWaterMeterBinding
import com.yuzhny.mykis.databinding.ItemWaterReadingBinding
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class WaterReadingAdapter @Inject constructor() :ListAdapter<WaterReadingEntity ,WaterReadingAdapter.WaterReadingViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterReadingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WaterReadingAdapter.WaterReadingViewHolder(
            ItemWaterReadingBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WaterReadingViewHolder, position: Int) {
        val reading = getItem(position)
        holder.binding.apply {
            dateOt.text = reading.dateOt
            dateDo.text = reading.dateDo
            days.text = reading.days.toString()
            cubs.text = reading.kub.toString()
            last.text = reading.last.toString()
            current.text = reading.currant.toString()
            pokOt.text = reading.pokOt.toString()
            pokDo.text = reading.pokDo.toString()
            cubeDayCube.text = reading.kubDay.toString()
            qtyCube.text =reading.qtyKub.toString()
            rday.text = reading.rday.toString()
            if(!trueOrFalse(reading.avg)){
                cardAvg.visibility = View.GONE
                avgText.visibility = View.GONE
            }
        }
    }

    class WaterReadingViewHolder(var binding: ItemWaterReadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


    companion object DiffCallback : DiffUtil.ItemCallback<WaterReadingEntity>() {
        override fun areItemsTheSame(oldItem: WaterReadingEntity, newItem: WaterReadingEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WaterReadingEntity, newItem: WaterReadingEntity): Boolean {
            return oldItem == newItem
        }

    }
}