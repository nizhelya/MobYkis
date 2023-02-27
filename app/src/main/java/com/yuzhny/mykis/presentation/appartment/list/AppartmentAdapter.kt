package com.yuzhny.mykis.presentation.appartment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yuzhny.mykis.presentation.core.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzhny.mykis.databinding.ItemAppartmentListBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppartmentAdapter @Inject constructor( val appartmentListener: AppartmentListener):
    BaseAdapter<AppartmentEntity, AppartmentAdapter.AppartmentViewHolder>(AppartmentDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppartmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAppartmentListBinding.inflate(layoutInflater, parent, false)
        return AppartmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppartmentViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val appartment = getItem(position)
        holder.bindItem(appartmentListener)
        holder.idText.text = appartment.addressId.toString()
        holder.addressText.text = appartment.address
    }


    class AppartmentViewHolder(val binding:ItemAppartmentListBinding) : BaseAdapter.BaseViewHolder(binding.root) {
        fun bindItem(clickListener: AppartmentListener, appartment: AppartmentEntity) {
            binding.clickListener = clickListener
        }
    }

    }
    class AppartmentDiffCallback : DiffUtil.ItemCallback<AppartmentEntity>() {

        override fun areItemsTheSame(
            oldItem: AppartmentEntity,
            newItem: AppartmentEntity
        ): Boolean {
            return oldItem.addressId == newItem.addressId
        }

        override fun areContentsTheSame(
            oldItem: AppartmentEntity,
            newItem: AppartmentEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}
class AppartmentListener(val clickListener: (appartment: AppartmentEntity) -> Unit) {
    fun onClick(appartment: AppartmentEntity) = clickListener(appartment)
}