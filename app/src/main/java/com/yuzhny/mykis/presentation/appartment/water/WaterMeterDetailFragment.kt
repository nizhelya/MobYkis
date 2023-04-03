package com.yuzhny.mykis.presentation.appartment.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.databinding.FragmentWaterMeterDetailBinding
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import com.yuzhny.mykis.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaterMeterDetailFragment : BaseFragment() {

    private val waterViewModel : WaterViewModel by activityViewModels()
    private var _binding : FragmentWaterMeterDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWaterMeterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waterViewModel.waterMeter.observe(this.viewLifecycleOwner){
            binding.apply {
                model.text = it.model
                number.text = it.nomer
                place.text = it.place
                position.text = it.position
                stoki.isChecked = trueOrFalse(it.st)
                general.isChecked = trueOrFalse(it.avg)
                zdate.text = it.zdate
                sdate.text = it.sdate
                pdate.text = it.pdate
                fdate.text = it.fpdate
                stop.isChecked = trueOrFalse(it.spisan)
            }
        }
    }

}