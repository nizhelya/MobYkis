package com.yuzhny.mykis.presentation.appartment.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentServiceListBinding
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.presentation.appartment.add.adapter.StreetArrayAdapter
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_service.*
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ServiceListFragment : BaseFragment() {

    private val listViewModel: AppartmentListViewModel by activityViewModels()
    private val serviceViewModel: ServiceViewModel by activityViewModels()

    private var _binding: FragmentServiceListBinding? = null
    private val binding get() = _binding!!

    private var osbb :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        serviceViewModel.apply {
            onSuccess(servicesFlat , ::handleService)
            onFailure(failureData, ::handleFailure)
        }
        // Inflate the layout for this fragment
        _binding = FragmentServiceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        serviceViewModel.getFlatService(
                listViewModel.currentAddress,
                listViewModel.currentHouse,
                0,
                1,
                1
        )
        listViewModel.appartment.observe(this.viewLifecycleOwner){
            osbb = it.osbb
            binding.buttonKv.text = osbb
        }
        serviceViewModel.totalDebt.observe(this.viewLifecycleOwner){
            it?.let {
                binding.apply {
                    dolg1.text =  it.dolg1.toString()
                    dolg2.text =  it.dolg2.toString()
                    dolg3.text =  it.dolg3.toString()
                    dolg4.text =  it.dolg4.toString()
                    dolg.text =   "${it.dolg.toString()}₴"
                    borgText.text = getString(R.string.borg_text,
                        SimpleDateFormat("dd/MM/yyy").format(Date())
                    )
                }
            }

        }
        binding.buttonVodokanal.setOnClickListener {
            serviceViewModel.currentService = 1
            serviceViewModel.currentServiceTitle = getString(R.string.vodokanal)
            findNavController().navigate(R.id.action_viewPagerFragment_to_serviceDetailFragment)
        }
            binding.buttonYtke.setOnClickListener {
                serviceViewModel.currentService = 2
                serviceViewModel.currentServiceTitle = getString(R.string.ytke)
                findNavController().navigate(R.id.action_viewPagerFragment_to_serviceDetailFragment)
            }
            binding.buttonTbo.setOnClickListener {
                serviceViewModel.currentService = 3
                serviceViewModel.currentServiceTitle = getString(R.string.yzhtrans)
                findNavController().navigate(R.id.action_viewPagerFragment_to_serviceDetailFragment)
            }
            binding.buttonKv.setOnClickListener {
                serviceViewModel.currentService = 4
                serviceViewModel.currentServiceTitle = osbb
                findNavController().navigate(R.id.action_viewPagerFragment_to_serviceDetailFragment)
            }

    }
    private fun handleService(serviceEntity: List<ServiceEntity>?){
        if (serviceEntity != null && serviceEntity.isNotEmpty()) {
            serviceViewModel.getTotalService(listViewModel.currentAddress)
            binding.loadingView.visibility = View.GONE
            binding.mainConstraint.visibility = View.VISIBLE
        }
    }
}