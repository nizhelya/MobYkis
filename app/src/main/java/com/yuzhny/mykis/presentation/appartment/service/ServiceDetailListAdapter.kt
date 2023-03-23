package com.yuzhny.mykis.presentation.appartment.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.databinding.ItemFamilyListBinding
import com.yuzhny.mykis.databinding.ItemServiceBinding
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.presentation.appartment.family.FamilyListAdapter
import com.yuzhny.mykis.presentation.appartment.util.isEmptyFun
import dagger.hilt.android.scopes.FragmentScoped
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@FragmentScoped
class ServiceDetailListAdapter @Inject constructor() :ListAdapter <ServiceEntity , ServiceDetailListAdapter.ServiceViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServiceDetailListAdapter.ServiceViewHolder(
            ItemServiceBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = getItem(position)
        val dateUnix = SimpleDateFormat("yyyy-MM-dd").parse(service.data)
        holder.binding.apply {

            dateText.text = SimpleDateFormat("LLLL yyyy", Locale("ua" , "UA")).format(Date(dateUnix.time)  )
            zadol1.text = service.zadol1.toString()
            zadol2.text = service.zadol2.toString()
            zadol3.text = service.zadol3.toString()
            zadol4.text = service.zadol4.toString()
            service1.text = service.service1
            service2.text = service.service2
            service3.text = service.service3
            service4.text = service.service4
            nachisleno1.text = service.nachisleno1.toString()
            nachisleno2.text = service.nachisleno2.toString()
            nachisleno3.text = service.nachisleno3.toString()
            nachisleno4.text = service.nachisleno4.toString()
            oplacheno1.text = service.oplacheno1.toString()
            oplacheno2.text = service.oplacheno2.toString()
            oplacheno3.text = service.oplacheno3.toString()
            oplacheno4.text = service.oplacheno4.toString()
            dolg1.text = service.dolg1.toString()
            dolg2.text = service.dolg2.toString()
            dolg3.text = service.dolg3.toString()
            dolg4.text = service.dolg4.toString()
            if(!isEmptyFun(service.service2)){
                zadol2.visibility = View.GONE
                nachisleno2.visibility = View.GONE
                oplacheno2.visibility = View.GONE
                dolg2.visibility = View.GONE
                service2.visibility = View.GONE
            }
            if(!isEmptyFun(service.service3)){
                zadol3.visibility = View.GONE
                nachisleno3.visibility = View.GONE
                oplacheno3.visibility = View.GONE
                dolg3.visibility = View.GONE
                service3.visibility = View.GONE
            }
            if(!isEmptyFun(service.service4)){
                zadol4.visibility = View.GONE
                nachisleno4.visibility = View.GONE
                oplacheno4.visibility = View.GONE
                dolg4.visibility = View.GONE
                service4.visibility = View.GONE
            }
        }
    }


    class ServiceViewHolder(var binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ServiceEntity>() {
        override fun areItemsTheSame(oldItem: ServiceEntity, newItem: ServiceEntity): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ServiceEntity, newItem: ServiceEntity): Boolean {
            return oldItem == newItem
        }

    }


}