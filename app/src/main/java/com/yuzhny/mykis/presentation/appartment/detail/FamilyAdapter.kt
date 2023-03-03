package com.yuzhny.mykis.presentation.appartment.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.R
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
        holder.binding.surname.text = family.surname
        holder.binding.fistname.text = family.fistname
        holder.binding.lastname.text = family.lastname
        holder.binding.born.text = family.born
        holder.binding.relationship.text = family.rodstvo
        holder.binding.sex.text = family.sex

        val isExpandable: Boolean = family.isExpandable
         if (isExpandable){
             holder.binding.group.visibility = View.VISIBLE
             holder.binding.viewOpen.setImageResource(R.drawable.ic_expand_less)
         }else {
             holder.binding.group.visibility = View.GONE
             holder.binding.viewOpen.setImageResource(R.drawable.ic_expand_more)
         }

        holder.binding.constraintLayout.setOnClickListener {
            family.isExpandable = !family.isExpandable
            notifyItemChanged(position , Unit)
        }
    }

    class FamilyViewHolder(var binding: ItemFamilyListBinding) :
        RecyclerView.ViewHolder(binding.root) {
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