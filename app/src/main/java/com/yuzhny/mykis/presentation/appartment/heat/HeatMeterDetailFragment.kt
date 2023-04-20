package com.yuzhny.mykis.presentation.appartment.heat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentHeatMeterBinding
import com.yuzhny.mykis.databinding.FragmentHeatMeterDetailBinding
import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HeatMeterDetailFragment : BaseFragment() {

    private val listViewModel : AppartmentListViewModel by activityViewModels()
    private val heatViewModel : HeatViewModel by activityViewModels()

    private var _binding : FragmentHeatMeterDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var heatReadingAdapter: HeatReadingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        heatViewModel.apply {
            onSuccess(heatReadings , ::handleReadings)
            onFailure(failureData, ::handleFailure)
        }
        _binding = FragmentHeatMeterDetailBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heatViewModel.getHeatReadings()
        heatViewModel.heatMeter.observe(this.viewLifecycleOwner){
            binding.apply {
                model.text = it.model
                number.text = it.number
                area.text = it.area.toString()
                koef.text = it.koef
                edizm.text = it.edizm
                sdate.text = it.sdate
                pdate.text = it.pdate
                fdate.text = it.fpdate
                spisan.isChecked = trueOrFalse(it.spisan)
                out.isChecked = trueOrFalse(it.out)
            }
        }
        heatViewModel.heatReading.observe(this.viewLifecycleOwner){
            binding.apply {
                last.text = it.last.toString()
                cur.text = it.currant.toString()
                date.text = it.dateDo
            }
        }
        binding.recyclerView.adapter = heatReadingAdapter
    }

    private fun handleReadings(readingEntity: List<HeatReadingEntity>?){
        if(readingEntity!!.isNotEmpty()){
            heatReadingAdapter.submitList(readingEntity)
            heatViewModel.getHeatReading(readingEntity[0])
        }
    }

}