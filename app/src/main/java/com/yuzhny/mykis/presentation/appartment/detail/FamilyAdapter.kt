package com.yuzhny.mykis.presentation.appartment.detail

import androidx.recyclerview.widget.DiffUtil
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class FamilyAdapter {

    companion object DiffCallback : DiffUtil.ItemCallback<FamilyEntity>(){
        override fun areItemsTheSame(oldItem: FamilyEntity, newItem: FamilyEntity): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FamilyEntity, newItem: FamilyEntity): Boolean {
            return  oldItem == newItem
        }

    }
}