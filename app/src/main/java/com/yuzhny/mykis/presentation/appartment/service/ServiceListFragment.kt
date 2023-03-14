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
        binding.button2.setOnClickListener{
            serviceViewModel.getFlatService(
                listViewModel.currentAddress,
                listViewModel.currentHouse,
                1,
                1,
                )
        }
    }

}