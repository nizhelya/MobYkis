package com.yuzhny.mykis.presentation.appartment.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentServiceDetailBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.presentation.MainActivity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServiceDetailFragment @Inject constructor() : BaseFragment() {

    private val serviceViewModel: ServiceViewModel by activityViewModels()
    private val listViewModel: AppartmentListViewModel by activityViewModels()

    private var _binding: FragmentServiceDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var serviceAdapter: ServiceDetailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        serviceViewModel.apply {
//            onSuccess(servicesFlat , ::handleServices)
//        }
        _binding = FragmentServiceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        serviceViewModel.getFlatService(
            listViewModel.currentAddress,
            listViewModel.currentHouse,
            serviceViewModel.currentService,
            0,
            1,
        )
        serviceViewModel.servicesFlat.observe(this.viewLifecycleOwner) { i ->
            i?.let {
                serviceAdapter.submitList(it)
            }
        }
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(true)
        actionBar.title = serviceViewModel.currentServiceTitle
        binding.recyclerView.adapter = serviceAdapter
    }

//    private fun handleService(serviceEntity:  List<ServiceEntity>?) {
//        if (serviceEntity != null && serviceEntity.isNotEmpty()) {
//            serviceAdapter.submitList(serviceEntity)
//        }
//    }
//
}