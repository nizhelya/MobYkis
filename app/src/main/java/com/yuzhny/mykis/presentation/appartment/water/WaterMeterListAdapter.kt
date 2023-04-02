package com.yuzhny.mykis.presentation.appartment.water

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.ItemFamilyListBinding
import com.yuzhny.mykis.databinding.ItemWaterMeterBinding
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.water.WaterMeterEntity
import com.yuzhny.mykis.presentation.appartment.family.FamilyListAdapter
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

class WaterMeterListAdapter() :ListAdapter<WaterMeterEntity,WaterMeterListAdapter.WaterMeterViewHolder >(DiffCallback) {

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
            if(trueOrFalse(meter.spisan)){
                firstLayout.setBackgroundColor(Color.parseColor("#999999"))
                firstLayout.alpha = .4f
                spisanText.text = "Списаний"
            }else{
                spisanText.visibility = View.GONE
            }
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