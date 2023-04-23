package com.yuzhny.mykis.presentation.appartment.heat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentHeatMeterBinding
import com.yuzhny.mykis.databinding.FragmentWaterListBinding
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.appartment.water.WaterViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HeatMeterListFragment : BaseFragment() {

    private val listViewModel : AppartmentListViewModel by activityViewModels()
    private val heatViewModel : HeatViewModel by activityViewModels()

    private var _binding : FragmentHeatMeterBinding ? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var heatAdapter : HeatMeterListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        heatViewModel.apply {
            onSuccess(heatMeters , ::handleHeatMeters)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeatMeterBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heatViewModel.getHeatMeters(listViewModel.currentAddress)
        binding.recyclerView.adapter = heatAdapter
        heatAdapter.heatMeterShortListener.onItemClick = {
            heatViewModel.getHeatMeter(it)
            heatViewModel.currentTeplomerId = it.teplomerId
            Navigation.findNavController(mainNavView).navigate(R.id.action_bottomNavigationFragment_to_heatMeterDetailFragment)
        }
    }

    private fun handleHeatMeters(heatMeters:List<HeatMeterEntity>?){
        heatAdapter.submitList(heatMeters)
    }


}