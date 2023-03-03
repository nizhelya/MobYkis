package com.yuzhny.mykis.presentation.appartment.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.databinding.ItemAppartmentListBinding
import com.yuzhny.mykis.databinding.ItemFamilyListBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListAdapter
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class FamilyListAdapter @Inject constructor() : ListAdapter<FamilyEntity, FamilyListAdapter.FamilyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FamilyListAdapter.FamilyViewHolder(
            ItemFamilyListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        val family = getItem(position)
        holder.bind(family)
        val isExpandable : Boolean = family.isExpandable
        holder.binding.constraintLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE
        holder.binding.constraintLayout.setOnClickListener {
            family.isExpandable = !family.isExpandable
            notifyItemChanged(position)
        }
    }

    class FamilyViewHolder(var binding: ItemFamilyListBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(family : FamilyEntity){
                binding.fistname.text = family.fistname
                binding.surname.text = family.surname
                binding.lastname.text = family.lastname
                binding.born.text = family.born
             }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FamilyEntity>() {
        override fun areItemsTheSame(oldItem: FamilyEntity, newItem: FamilyEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FamilyEntity, newItem: FamilyEntity): Boolean {
            return oldItem == newItem
        }

    }


}