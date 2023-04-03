package com.yuzhny.mykis.presentation.appartment.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yuzhny.mykis.presentation.appartment.bti.BtiFragment
import com.yuzhny.mykis.presentation.appartment.family.FamilyListFragment
import com.yuzhny.mykis.presentation.appartment.payment.PaymentFlatListFragment
import com.yuzhny.mykis.presentation.appartment.service.ServiceListFragment
import com.yuzhny.mykis.presentation.appartment.water.WaterListFragment

class ChildFragmentStateAdapter(fragmentManager: FragmentManager , lifecycle: Lifecycle)
    :FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int  =  4


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FamilyListFragment()
            1-> BtiFragment()
            2 -> ServiceListFragment()
            else -> WaterListFragment()
        }
    }
}