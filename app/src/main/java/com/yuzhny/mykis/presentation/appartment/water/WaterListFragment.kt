package com.yuzhny.mykis.presentation.appartment.water

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentWaterListBinding
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.presentation.MainActivity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WaterListFragment : BaseFragment() {

    private val listViewModel : AppartmentListViewModel by activityViewModels()
    private val waterViewModel : WaterViewModel by activityViewModels()

    private var _binding : FragmentWaterListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var waterAdapter : WaterMeterListAdapter
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
        val mainNavView: View = requireActivity().findViewById<View>(R.id.nav_host_fragment)

        waterAdapter.waterMeterShortListener.onItemClick = {
            waterViewModel.getWaterMeter(it)
            waterViewModel.currentVodomerId = it.vodomerId
            Navigation.findNavController(mainNavView).navigate(R.id.action_bottomNavigationFragment_to_waterMeterDetailFragment)
        }
    }

    private fun handleWaterMeters(waterMeters:List<WaterMeterEntity>?){
        waterAdapter.submitList(waterMeters)
    }

}