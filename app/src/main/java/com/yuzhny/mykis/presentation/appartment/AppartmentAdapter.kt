package com.yuzhny.mykis.presentation.appartment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.yuzhny.mykis.presentation.core.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzhny.mykis.databinding.ItemAppartmentListBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppartmentAdapter @Inject constructor():
    BaseAdapter<AppartmentEntity, AppartmentAdapter.AppartmentViewHolder>(AppartmentDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppartmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAppartmentListBinding.inflate(layoutInflater, parent, false)
        return AppartmentViewHolder(binding)
    }

    class AppartmentViewHolder(val binding:ItemAppartmentListBinding) : BaseAdapter.BaseViewHolder(binding.root) {
        init {
//            binding.btnRemoveAppartment.setOnClickListener {
//                onClick?.onClick(item, it)
//            }
        }

        override fun onBind(item: Any) {
            (item as? AppartmentEntity)?.let {
                binding.idText.text = item.addressId.toString()
                binding.addressText.text = item.address
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