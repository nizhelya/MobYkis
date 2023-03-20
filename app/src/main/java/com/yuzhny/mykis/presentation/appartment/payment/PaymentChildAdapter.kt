package com.yuzhny.mykis.presentation.appartment.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.databinding.ChildPaymentListBinding
import com.yuzhny.mykis.databinding.ItemPaymentListBinding
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class PaymentChildAdapter @Inject constructor(
) : ListAdapter<PaymentEntity ,
        PaymentChildAdapter.PaymentChildViewHolder >
    (DiffCallback){
    class PaymentChildViewHolder(var binding: ChildPaymentListBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentChildViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PaymentChildAdapter.PaymentChildViewHolder(
            ChildPaymentListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PaymentChildViewHolder, position: Int) {
        val paymentChild = getItem(position)
        holder.binding.apply {
            date.text = paymentChild.data
            kv.text = paymentChild.kvartplata.toString()
            remont.text = paymentChild.remont.toString()
            otoplenie.text = paymentChild.otoplenie.toString()
            vodokanal.text = paymentChild.voda.toString()
            tbo.text = paymentChild.tbo.toString()
            summary.text = paymentChild.summa.toString()
            prixod.text = paymentChild.prixod
            operator.text = paymentChild.nomer
        }
    }
}