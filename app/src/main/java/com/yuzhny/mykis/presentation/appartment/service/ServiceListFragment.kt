package com.yuzhny.mykis.presentation.appartment.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentListAppartmentBinding
import com.yuzhny.mykis.databinding.FragmentServiceListBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.service.request.ServiceParams
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_service_list.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ServiceListFragment : BaseFragment() {

    private val listViewModel: AppartmentListViewModel by activityViewModels()
    private val serviceViewModel: ServiceViewModel by activityViewModels()

    private var _binding: FragmentServiceListBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        serviceViewModel.apply {
            onSuccess(servicesFlat, ::handleTotalDebt)
            onFailure(failureData, ::handleFailure)
        }
        // Inflate the layout for this fragment
        _binding = FragmentServiceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serviceViewModel.getFlatService(
            ServiceParams(
                listViewModel.currentAddress,
                listViewModel.currentHouse,
                0,
                1,
                0
            )
        )
        serviceViewModel.totalDebt.observe(this.viewLifecycleOwner){
            binding.apply {
                dolg1.text = it.dolg1.toString()
                dolg2.text = it.dolg2.toString()
                dolg3.text = it.dolg3.toString()
                dolg4.text = it.dolg4.toString()
                dolg.text = it.dolg.toString()
            }
        }
        listViewModel.appartment.observe(this.viewLifecycleOwner) {
            binding.buttonTbo.text = it.osbb
        }

        binding.buttonVodokanal.setOnClickListener {
            serviceViewModel.getFlatService(
                ServiceParams(
                    listViewModel.currentAddress,
                    listViewModel.currentHouse,
                    1,
                    0,
                    1
                )
            )
            binding.buttonYtke.setOnClickListener {
                serviceViewModel.getFlatService(
                    ServiceParams(
                        listViewModel.currentAddress,
                        listViewModel.currentHouse,
                        2,
                        0,
                        1
                    )
                )
            }
            binding.buttonYzhtrans.setOnClickListener {
                serviceViewModel.getFlatService(
                    ServiceParams(
                        listViewModel.currentAddress,
                        listViewModel.currentHouse,
                        3,
                        0,
                        1
                    )
                )
            }
            binding.buttonTbo.setOnClickListener {
                serviceViewModel.getFlatService(
                    ServiceParams(
                        listViewModel.currentAddress,
                        listViewModel.currentHouse,
                        4,
                        0,
                        1
                    )
                )
            }
        }
    }

    private fun handleTotalDebt(serviceEntity: List<ServiceEntity>?) {
        if (serviceEntity != null && serviceEntity.isNotEmpty()) {
            serviceViewModel.getDebtFromCache(listViewModel.currentAddress)
        }
    }
}