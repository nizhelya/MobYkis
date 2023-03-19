package com.yuzhny.mykis.presentation.appartment.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yuzhny.mykis.databinding.ItemPaymentListBinding
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.payment.PaymentEntity
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.data.cache.dao.PaymentDao
import com.yuzhny.mykis.data.cache.payment.PaymentCacheImpl

@FragmentScoped
class PaymentListAdapter @Inject constructor(
) : ListAdapter<
        PaymentEntity,
        PaymentListAdapter.PaymentViewHolder
        >(
    DiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PaymentListAdapter.PaymentViewHolder(
            ItemPaymentListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = getItem(position)
        holder.binding.yearText.text = payment.year.toString()
    }
    class PaymentViewHolder(var binding: ItemPaymentListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PaymentEntity>() {
        override fun areItemsTheSame(oldItem: PaymentEntity, newItem: PaymentEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PaymentEntity, newItem: PaymentEntity): Boolean {
            return oldItem == newItem
        }

    }


}