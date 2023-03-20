package com.yuzhny.mykis.presentation.appartment.payment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.yuzhny.mykis.databinding.ItemPaymentListBinding
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.payment.PaymentEntity
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.cache.dao.PaymentDao
import com.yuzhny.mykis.data.cache.payment.PaymentCacheImpl
import com.yuzhny.mykis.databinding.ChildPaymentListBinding
import kotlinx.coroutines.coroutineScope
import java.time.Year

@FragmentScoped
class PaymentListAdapter @Inject constructor(
    private val paymentCacheImpl: PaymentCacheImpl
) : ListAdapter<
        PaymentEntity,
        PaymentListAdapter.PaymentViewHolder
        >(
    DiffCallback
) {
    private val childAdapter = PaymentChildAdapter()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PaymentListAdapter.PaymentViewHolder(
            ItemPaymentListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = getItem(position)
        holder.binding.apply {
            yearText.text = payment.year.toString()
            if (payment.isExpandable) {
                recyclerView.visibility = View.VISIBLE
                childAdapter.submitList(
                    paymentCacheImpl.getPaymentFromYearFlat(payment.addressID , payment.year)
                )
                recyclerView.adapter = childAdapter
                viewOpen.setImageResource(R.drawable.ic_expand_less)
            } else {
                recyclerView.visibility = View.GONE
                viewOpen.setImageResource(R.drawable.ic_expand_more)
            }
            cardView.setOnClickListener {
                isAnyItemExpanded(position)
                payment.isExpandable = !payment.isExpandable
                notifyItemChanged(position, Unit)
            }
        }
    }
    private fun isAnyItemExpanded(position: Int) {
        val temp = currentList.indexOfFirst {
            it.isExpandable
        }
        if(temp >= 0 && temp != position){
            currentList[temp].isExpandable = false
            notifyItemChanged(temp)
        }
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