package com.yuzhny.mykis.presentation.appartment.service

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentServiceListBinding
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_service_list.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

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
            onSuccess(servicesFlat , ::handleService)
            onFailure(failureData, ::handleFailure)
        }
        // Inflate the layout for this fragment
        _binding = FragmentServiceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.textView.text = SimpleDateFormat("dd-MMM-yyy").format(Date(dateUnix.time))
        serviceViewModel.getFlatService(
                listViewModel.currentAddress,
                listViewModel.currentHouse,
                0,
                1,
                1
        )
        serviceViewModel.getDebtFromCache(listViewModel.currentAddress)
        serviceViewModel.totalDebt.observe(this.viewLifecycleOwner){
            it?.let {
                binding.apply {
                    val dateUnix = SimpleDateFormat("yyyyMMdd").parse(it.data)
                    dolg1.text =  it.dolg1.toString()
                    dolg2.text =  it.dolg2.toString()
                    dolg3.text =  it.dolg3.toString()
                    dolg4.text =  it.dolg4.toString()
                    dolg.text =   "${it.dolg.toString()}₴"
                    borgText.text = getString(R.string.borg_text,
                        SimpleDateFormat("dd/MM/yyy").format(Date(dateUnix.time)))
                }
            }

        }
        listViewModel.appartment.observe(this.viewLifecycleOwner) {
            binding.buttonTbo.text = it.osbb
        }

        binding.buttonVodokanal.setOnClickListener {
            serviceViewModel.getFlatService(
                    listViewModel.currentAddress,
                    listViewModel.currentHouse,
                    1,
                    0,
                    1
            )
            binding.buttonYtke.setOnClickListener {
                serviceViewModel.getFlatService(
                        listViewModel.currentAddress,
                        listViewModel.currentHouse,
                        2,
                        0,
                        1,
                )
            }
            binding.buttonYzhtrans.setOnClickListener {
                serviceViewModel.getFlatService(
                        listViewModel.currentAddress,
                        listViewModel.currentHouse,
                        3,
                        0,
                        1,
                )
            }
            binding.buttonTbo.setOnClickListener {
                serviceViewModel.getFlatService(
                        listViewModel.currentAddress,
                        listViewModel.currentHouse,
                        4,
                        0,
                        1,
                )
            }
        }
    }
    private fun handleService(appartmentEntity:  List<ServiceEntity>?) {
        if (appartmentEntity != null && appartmentEntity.isNotEmpty()) {
            serviceViewModel.getDebtFromCache(listViewModel.currentAddress)
        }
    }
}