package com.yuzhny.mykis.presentation.appartment.heat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.databinding.ItemHeatReadingBinding
import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class HeatReadingAdapter @Inject constructor() :
    ListAdapter<HeatReadingEntity, HeatReadingAdapter.HeatReadingViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeatReadingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeatReadingAdapter.HeatReadingViewHolder(
            ItemHeatReadingBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HeatReadingViewHolder, position: Int) {
        val reading = getItem(position)
        holder.binding.apply {
            dateOt.text = reading.dateOt
            dateDo.text = reading.dateDo
            days.text = reading.days.toString()
            rate.text = reading.tarif.toString()
            coefficient.text = reading.koef
            qty.text = reading.qty.toString()
            gkal.text = reading.gkal.toString()
//            cubs.text = reading.kub.toString()
            last.text = reading.last.toString()
            current.text = reading.currant.toString()
            pokOt.text = reading.pokOt
            pokDo.text = reading.pokDo
            gkalDay.text = reading.gkalDay
            gkalRasch.text =reading.gkalRasch
            dayAvg.text = reading.dayAvg
            edizm.text = reading.edizm
            if(!trueOrFalse(reading.avg)){
                cardAvg.visibility = View.GONE
                avgText.visibility = View.GONE
            }
         }
    }


    class HeatReadingViewHolder(var binding: ItemHeatReadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


    companion object DiffCallback : DiffUtil.ItemCallback<HeatReadingEntity>() {
        override fun areItemsTheSame(oldItem: HeatReadingEntity, newItem: HeatReadingEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HeatReadingEntity, newItem: HeatReadingEntity): Boolean {
            return oldItem == newItem
        }

    }
}