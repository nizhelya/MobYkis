package com.yuzhny.mykis.presentation.appartment.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.databinding.EditDialogLayoutBinding
import com.yuzhny.mykis.databinding.FragmentWaterMeterDetailBinding
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class WaterMeterDetailFragment : BaseFragment() {

    private val waterViewModel : WaterViewModel by activityViewModels()
    private var _binding : FragmentWaterMeterDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var waterReadingAdapter: WaterReadingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        waterViewModel.apply {
            onSuccess(waterReadings , ::handleReadings)
            onSuccess(resultText , ::handleResultText)
            onFailure(failureData, ::handleFailure)

        }
        _binding = FragmentWaterMeterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waterViewModel.getWaterReadings(waterViewModel.currentVodomerId)
        waterViewModel.waterReading.observe(this.viewLifecycleOwner){
            binding.apply {
                last.text = it.last.toString()
                cur.text = it.currant.toString()
                cubs.text = it.kub.toString()
            }
        }
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
        binding.apply {
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.adapter = waterReadingAdapter
            date.text = SimpleDateFormat("dd-MM-yyy").format(Date())
            addReadingButton.setOnClickListener{
                showAddNewReading()
            }
        }
    }
    private fun handleReadings(readingEntity: List<WaterReadingEntity>?){
        if(readingEntity!!.isNotEmpty()){
//        waterReadingAdapter.submitList(readingEntity)
        waterViewModel.getWaterReading(readingEntity[0])
        }
    }
    private fun showAddNewReading() {

        val dialogLayout  = layoutInflater.inflate(R.layout.edit_dialog_layout , null)
        val addReading = dialogLayout.findViewById<TextView>(R.id.add_reading)
        addReading.visibility = View.VISIBLE
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.add_reading_title))
            .setCancelable(true)
            .setIcon(R.drawable.ic_add)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->

            }
            .setPositiveButton(getString(R.string.add)) { _, _ ->
                waterViewModel.addNewWaterReading(addReading.text.toString().toInt())
            }
            .setView(dialogLayout)
            .show()
    }
    private fun handleResultText(getSimpleResponse: GetSimpleResponse?) {
        getSimpleResponse?.let {
            if(it.success == 1){
                it.success = 0
                Toast.makeText(requireContext() ,getString(R.string.reading_added), Toast.LENGTH_SHORT ).show()
                waterViewModel.getWaterReadings(waterViewModel.currentVodomerId)
            }
        }
    }
}