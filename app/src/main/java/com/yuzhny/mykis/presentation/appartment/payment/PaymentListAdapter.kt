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
import com.yuzhny.mykis.domain.payment.PaymentItemEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.Year

@FragmentScoped
class PaymentListAdapter @Inject constructor(
) : ListAdapter<
        PaymentItemEntity,
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
        expandItem(payment, holder , position)
        val childAdapter = PaymentChildAdapter()
        holder.binding.apply {
            childAdapter.submitList(payment.paymentsList)
            recyclerView.adapter = childAdapter
            yearText.text = payment.year.toString()
            recyclerView.setHasFixedSize(true)

        }
        holder.binding.cardView.setOnClickListener {
            isAnyItemExpanded(position)
            payment.isExpandable = !payment.isExpandable
            notifyItemChanged(position, Unit)
            }

    }
    private fun expandItem(payment:PaymentItemEntity, holder:PaymentViewHolder , position: Int){
      if(payment.isExpandable){
          holder.binding.recyclerView.visibility = View.VISIBLE
          holder.binding.viewOpen.setImageResource(R.drawable.ic_expand_less)
      }else{
          holder.binding.recyclerView.visibility = View.GONE
          holder.binding.viewOpen.setImageResource(R.drawable.ic_expand_more)
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

    companion object DiffCallback : DiffUtil.ItemCallback<PaymentItemEntity>() {
        override fun areItemsTheSame(oldItem: PaymentItemEntity, newItem: PaymentItemEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PaymentItemEntity, newItem: PaymentItemEntity): Boolean {
            return oldItem == newItem
        }

    }


}