package com.yuzhny.mykis.presentation.appartment.family

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.ItemFamilyListBinding
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListAdapter
import com.yuzhny.mykis.presentation.appartment.util.hideIfEmpty
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class FamilyListAdapter @Inject constructor() : ListAdapter<FamilyEntity, FamilyListAdapter.FamilyViewHolder>(
    DiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FamilyListAdapter.FamilyViewHolder(
            ItemFamilyListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        val family = getItem(position)
        holder.binding.apply {
            surname.text = family.surname
            fistname.text = family.fistname
            lastname.text = family.lastname
            born.text = family.born
            relationship.text = family.rodstvo
            sex.text = family.sex
            phone.text = family.phone
            subsidia.isChecked = trueOrFalse(family.subsidia)
            vkl.isChecked = trueOrFalse(family.vkl)
            hideIfEmpty(family.sex , linearSex )
            hideIfEmpty(family.rodstvo , linearRelationship)
            hideIfEmpty(family.phone ,linearPhone)
        }



        val isExpandable: Boolean = family.isExpandable
         if (isExpandable){
             holder.binding.sex.visibility = View.VISIBLE
             holder.binding.sexText.visibility = View.VISIBLE
             holder.binding.born.visibility = View.VISIBLE
             holder.binding.bornText.visibility = View.VISIBLE
             holder.binding.relationship.visibility = View.VISIBLE
             holder.binding.relationshipText.visibility = View.VISIBLE
             holder.binding.phone.visibility = View.VISIBLE
             holder.binding.phoneText.visibility = View.VISIBLE
             holder.binding.subsidia.visibility = View.VISIBLE
             holder.binding.subsidiaText.visibility = View.VISIBLE
             holder.binding.vkl.visibility = View.VISIBLE
             holder.binding.vklText.visibility = View.VISIBLE
             holder.binding.viewOpen.setImageResource(R.drawable.ic_expand_less)
         }else {
             holder.binding.sex.visibility = View.GONE
             holder.binding.sexText.visibility = View.GONE
             holder.binding.born.visibility = View.GONE
             holder.binding.bornText.visibility = View.GONE
             holder.binding.relationship.visibility = View.GONE
             holder.binding.relationshipText.visibility = View.GONE
             holder.binding.phone.visibility = View.GONE
             holder.binding.phoneText.visibility = View.GONE
             holder.binding.subsidia.visibility = View.GONE
             holder.binding.subsidiaText.visibility = View.GONE
             holder.binding.vklText.visibility = View.GONE
             holder.binding.vkl.visibility = View.GONE
             holder.binding.viewOpen.setImageResource(R.drawable.ic_expand_more)
         }

        holder.binding.cardView.setOnClickListener {
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


