package com.yuzhny.mykis.presentation.appartment.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentListAppartmentBinding
import com.yuzhny.mykis.databinding.FragmentServiceListBinding
import com.yuzhny.mykis.domain.service.request.ServiceParams
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServiceListFragment : Fragment() {

    private val listViewModel : AppartmentListViewModel by activityViewModels()
    private val serviceViewModel : ServiceViewModel by activityViewModels()

    private var _binding : FragmentServiceListBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentServiceListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.appartment.observe(this.viewLifecycleOwner){
            binding.buttonTbo.text = it.osbb
        }
        binding.buttonVodokanal.setOnClickListener {
            serviceViewModel.getFlatService(
                ServiceParams(
                    listViewModel.currentAddress,
                    listViewModel.currentHouse,
                    1,
                    1
                )
            )
            binding.buttonYtke.setOnClickListener {
                serviceViewModel.getFlatService(
                    ServiceParams(
                        listViewModel.currentAddress,
                        listViewModel.currentHouse,
                        2,
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
                        1
                    )
                )
            }
        }
    }
}