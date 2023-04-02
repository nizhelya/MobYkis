package com.yuzhny.mykis.presentation.appartment.water

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentFamilyListBinding
import com.yuzhny.mykis.databinding.FragmentWaterListBinding
import com.yuzhny.mykis.domain.water.WaterMeterEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@AndroidEntryPoint
class WaterListFragment : BaseFragment() {

    private val listViewModel : AppartmentListViewModel by activityViewModels()
    private val waterViewModel : WaterViewModel by activityViewModels()

    private var _binding : FragmentWaterListBinding? = null
    private val binding get() = _binding!!

    private val waterAdapter :WaterMeterListAdapter = WaterMeterListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        waterViewModel.apply {
            onSuccess(waterMeters , ::handleWaterMeters)
            onFailure(failureData, ::handleFailure)
        }
        _binding = FragmentWaterListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waterViewModel.getWaterMeters(listViewModel.currentAddress)
        binding.recyclerView.adapter = waterAdapter
    }

    private fun handleWaterMeters(waterMeters:List<WaterMeterEntity>?){
        waterAdapter.submitList(waterMeters)
    }

}