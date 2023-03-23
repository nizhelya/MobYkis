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
import com.github.satoshun.coroutine.autodispose.view.autoDispose
import com.github.satoshun.coroutine.autodispose.view.autoDisposeScope
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.cache.dao.PaymentDao
import com.yuzhny.mykis.data.cache.payment.PaymentCacheImpl
import com.yuzhny.mykis.databinding.ChildPaymentListBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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

        holder.itemView.autoDisposeScope.launch {
            childAdapter.submitList(
                paymentCacheImpl.getPaymentFromYearFlat(payment.addressID, payment.year)
            )

        }
        var isExp = payment.isExpandable
        holder.binding.recyclerView.setHasFixedSize(true)
        holder.binding.recyclerView.visibility = if (isExp) View.VISIBLE else View.GONE

        holder.binding.recyclerView.adapter = childAdapter
        holder.binding.yearText.text = payment.year.toString()
        holder.binding.cardView.setOnClickListener {
            isAnyItemExpanded(position)
            payment.isExpandable = !payment.isExpandable
            notifyItemChanged(position, Unit)
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