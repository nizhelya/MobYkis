package com.yuzhny.mykis.presentation.appartment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentViewPagerBinding
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.appartment.service.ServiceViewModel


class InfoViewPagerFragment : Fragment() {
    private val listViewModel by activityViewModels<AppartmentListViewModel>()
    private val serviceViewModel : ServiceViewModel by activityViewModels()

    private var _binding : FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter =
            ChildInfoFragmentStateAdapter(childFragmentManager , viewLifecycleOwner.lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.list_family)
                else -> getString(R.string.bti)
            }
        }.attach()
        serviceViewModel.clearService()
    }


}