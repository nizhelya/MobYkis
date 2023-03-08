package com.yuzhny.mykis.presentation.appartment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.databinding.ItemAppartmentListBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class AppartmentListAdapter @Inject constructor(
    val appartmentShortListener: AppartmentShortListener ,
    val appartmentLongListener: AppartmentLongListener ):
    ListAdapter<AppartmentEntity , AppartmentListAdapter.AppartmentViewHolder>(DiffCallback) {

        class AppartmentViewHolder (var binding: ItemAppartmentListBinding)
            :RecyclerView.ViewHolder(binding.root){
            fun bind(appartmentShortListener: AppartmentShortListener ,
                     appartmentLongListener: AppartmentLongListener,
                     appartment: AppartmentEntity){
                binding.addressText.text = appartment.address
                binding.idText.text = appartment.addressId.toString()
                binding.data = appartment
                binding.clickShortListener = appartmentShortListener
                binding.clickLongListener = appartmentLongListener
                binding.executePendingBindings()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppartmentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return AppartmentViewHolder(
                ItemAppartmentListBinding.inflate(layoutInflater , parent , false)
            )
        }

        override fun onBindViewHolder(holder: AppartmentViewHolder, position: Int) {
            val appartment = getItem(position)
            holder.bind(appartmentShortListener ,appartmentLongListener,  appartment )
        }

        companion object DiffCallback : DiffUtil.ItemCallback<AppartmentEntity>(){
            override fun areItemsTheSame(oldItem: AppartmentEntity, newItem: AppartmentEntity): Boolean {
                return  oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AppartmentEntity, newItem: AppartmentEntity): Boolean {
                return  oldItem == newItem
            }

        }

}
class AppartmentShortListener @Inject constructor() {

    var onItemClick: ((AppartmentEntity) -> Unit)? = null

    fun onClick(data: AppartmentEntity) {
        onItemClick?.invoke(data)
    }


}

class AppartmentLongListener @Inject constructor(){
    var onItemLongClick: ((AppartmentEntity) -> Unit)? = null

    fun onLongClick(data: AppartmentEntity) :Boolean{
        onItemLongClick?.invoke(data)
        return true
    }
}