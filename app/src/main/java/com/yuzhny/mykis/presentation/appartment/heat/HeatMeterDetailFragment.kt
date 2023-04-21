package com.yuzhny.mykis.presentation.appartment.heat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.remote.GetSimpleResponse
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
import java.text.SimpleDateFormat
import java.util.*
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
            onSuccess(resultText , ::handleResultText)
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
                if(it.dateDo == SimpleDateFormat("yyy-MM-dd").format(Date())){
                    deleteReadingButton.visibility = View.VISIBLE
                }else deleteReadingButton.visibility = View.GONE
            }
        }
        binding.apply {
            recyclerView.adapter = heatReadingAdapter
            deleteReadingButton.setOnClickListener {
                showDeleteReadingDialog()
            }
            addReadingButton.setOnClickListener {
                showAddNewReading()
            }
        }
    }

    private fun handleReadings(readingEntity: List<HeatReadingEntity>?){
        if(readingEntity!!.isNotEmpty()){
            heatReadingAdapter.submitList(readingEntity)
            heatViewModel.getHeatReading(readingEntity[0])
        }
    }

    private fun showAddNewReading() {
        val dialogLayout  = layoutInflater.inflate(R.layout.edit_dialog_layout , null)
        val addReading = dialogLayout.findViewById<TextView>(R.id.add_reading)
        val currentReading = dialogLayout.findViewById<TextView>(R.id.current_reading)
        addReading.visibility = View.VISIBLE
        currentReading.visibility = View.VISIBLE
        currentReading.text = heatViewModel.currentReading.toString()
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.add_reading))
            .setCancelable(true)
            .setIcon(R.drawable.ic_add)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
            }
            .setPositiveButton(getString(R.string.add)) { _, _ ->
                heatViewModel.addNewHeatReading(addReading.text.toString().toDouble())
            }
            .setView(dialogLayout)
            .show()
    }
    private fun handleResultText(getSimpleResponse: GetSimpleResponse?) {
        getSimpleResponse?.let {
            if(it.success == 1){
                it.success = 0
                heatViewModel.getHeatReadings()
            }
        }
    }
    private fun showDeleteReadingDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.delete_reading_title))
//            .setMessage(getString((R.string.desc_delete),address))
            .setCancelable(true)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
            }
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                heatViewModel.deleteCurrentWaterReading()

            }
            .show()
    }

}