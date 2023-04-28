package com.yuzhny.mykis.presentation.appartment.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yuzhny.mykis.presentation.appartment.payment.list.PaymentFlatListFragment
import com.yuzhny.mykis.presentation.appartment.service.ServiceListFragment

class ChildPaymentFragmentAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int  =  2


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ServiceListFragment()
            else -> PaymentFlatListFragment()
        }
    }
}