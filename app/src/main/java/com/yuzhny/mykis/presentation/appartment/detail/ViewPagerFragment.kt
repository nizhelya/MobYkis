package com.yuzhny.mykis.presentation.appartment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentViewPagerBinding
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.service.request.ServiceParams
import com.yuzhny.mykis.presentation.MainActivity
import com.yuzhny.mykis.presentation.appartment.detail.adapter.ChildFragmentStateAdapter
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.appartment.service.ServiceViewModel
import com.yuzhny.mykis.presentation.core.ext.onSuccess


class ViewPagerFragment : Fragment() {
    private val listViewModel by activityViewModels<AppartmentListViewModel>()
    private val serviceViewModel : ServiceViewModel by activityViewModels()

    private var _binding : FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private val args : ViewPagerFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(true)
        listViewModel.appartment.observe(this.viewLifecycleOwner) {
            actionBar.title = it.address
        }
        _binding = FragmentViewPagerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter =
            ChildFragmentStateAdapter(childFragmentManager , viewLifecycleOwner.lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.list_family)
                1 -> getString(R.string.bti)
                2 -> getString(R.string.accrued)
                else -> getString(R.string.payment_list)
            }
        }.attach()
        listViewModel.currentAddress = args.addressId
        listViewModel.currentHouse = args.houseId
    }


}