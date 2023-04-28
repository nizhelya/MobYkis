package com.yuzhny.mykis.presentation.appartment.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yuzhny.mykis.presentation.appartment.bti.BtiFragment
import com.yuzhny.mykis.presentation.appartment.family.FamilyListFragment

class ChildInfoFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    :FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int  =  2


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FamilyListFragment()
            else -> BtiFragment()
        }
    }
}