package com.yuzhny.mykis.presentation.appartment.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentPaymentBinding
import com.yuzhny.mykis.databinding.FragmentServiceListBinding
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.appartment.service.ServiceViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class PaymentFragment : BaseFragment() {


    private val listViewModel: AppartmentListViewModel by activityViewModels()
    private val serviceViewModel: ServiceViewModel by activityViewModels()

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentPaymentBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.appartment.observe(this.viewLifecycleOwner){
            binding.kvText.text = it.osbb
        }
        serviceViewModel.totalDebt.observe(this.viewLifecycleOwner){
            it?.let {
                binding.apply {
                    vodokanalDolg.text =  it.dolg1.toString()
                    ytkeDolg.text =  it.dolg2.toString()
                    yzhtransDolg.text =  it.dolg3.toString()
                    kvDolg.text =  it.dolg4.toString()
                    totalDolg.text = it.dolg.toString()
                    editVodokanal.setText(it.dolg1.toString())
                    editYtke.setText(it.dolg2.toString())
                    editYzhtrans.setText(it.dolg3.toString())
                    editKv.setText(it.dolg4.toString())
                    checkVodokanal.setOnCheckedChangeListener{
                            _ , boolean -> checkEditText(boolean , editVodokanal , it.dolg1!!)
                    }
                    checkYtke.setOnCheckedChangeListener{
                            _ , boolean -> checkEditText(boolean , editYtke , it.dolg2!!)
                    }
                    checkYzhtrans.setOnCheckedChangeListener{
                            _ , boolean -> checkEditText(boolean , editYzhtrans , it.dolg3!!)
                    }
                    checkKv.setOnCheckedChangeListener{
                            _ , boolean -> checkEditText(boolean , editKv, it.dolg4!!)
                    }

                }
            }
        }
        binding.apply {
            editVodokanal.isEnabled = false
            editYtke.isEnabled = false
            editYzhtrans.isEnabled = false
            editKv.isEnabled = false
        }
    }

    private fun checkEditText(boolean: Boolean ,editText: EditText , dolg:Double){
        if(boolean){
            editText.isEnabled = boolean

        }else {
            editText.isEnabled = boolean
            }
        editText.setText(dolg.toString())
    }
}