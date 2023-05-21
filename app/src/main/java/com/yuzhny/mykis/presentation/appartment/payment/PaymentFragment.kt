package com.yuzhny.mykis.presentation.appartment.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentPaymentBinding
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.appartment.service.ServiceViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import ua.com.xpay.xpaylib.XPayLibPayment
import ua.com.xpay.xpaylib.model.OrderItem

@AndroidEntryPoint
class PaymentFragment : BaseFragment() {


    private val listViewModel: AppartmentListViewModel by activityViewModels()
    private val serviceViewModel: ServiceViewModel by activityViewModels()

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    val orderListData = listOf(
        OrderItem(
            "Apple iPad Pro 2022", "512Gb, WiFi, Silver", 1300.01
        ),
        OrderItem(
            "Apple iPhone 14 Pro", "512Gb, Silver", 1300.01
        ),
    )
    private val payment = XPayLibPayment {
        this.partnerToken = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxx"
        this.transactionId = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxx"
        this.googlePayGateway = "exampleGateway"
        this.googlePayGatewayMerchantId = "exampleMerchantId"
        this.terminalId = "111"
        this.payeeEmail = "test@test.com"
        this.payeePhone = "380xxxxxxxxx"
        this.payeeUserId = "1"
        this.payeeName = "Name"
        this.currency = "UAH"
        this.amount = 1.01
        this.purpose = "purpose"
        this.order = "example order"
        this.site = "example site"
        this.showOrderDetails = true
        this.orderItemList = orderListData
    }
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
        serviceViewModel.getFlatService(
            listViewModel.currentAddress,
            listViewModel.currentHouse,
            0,
            1,
            1
        )
        listViewModel.appartment.observe(this.viewLifecycleOwner){
            binding.kvText.text = it.osbb
        }
        serviceViewModel.totalPay.observe(this.viewLifecycleOwner){
            binding.totalPay.text = (getString(R.string.two_numbers_after_comma , it))
        }
        serviceViewModel.totalDebt.observe(this.viewLifecycleOwner){
            it?.let {
                binding.apply {
                    vodokanalDolg.text =  it.dolg1.toString()
                    ytkeDolg.text =  it.dolg2.toString()
                    yzhtransDolg.text =  it.dolg3.toString()
                    kvDolg.text =  it.dolg4.toString()
                    totalDolg.text = it.dolg.toString()
                    editVodokanal.setText(0.00.toString())
                    editYtke.setText(0.00.toString())
                    editYzhtrans.setText(0.00.toString())
                    editKv.setText(0.00.toString())
                    checkVodokanal.setOnCheckedChangeListener{
                            _ , boolean -> checkEditText(boolean , editVodokanal ,it.dolg1!! )
                    }
                    checkYtke.setOnCheckedChangeListener{
                            _ , boolean -> checkEditText(boolean , editYtke, it.dolg2!! )
                    }
                    checkYzhtrans.setOnCheckedChangeListener{
                            _ , boolean -> checkEditText(boolean , editYzhtrans , it.dolg3!! )
                    }
                    checkKv.setOnCheckedChangeListener{
                            _ , boolean -> checkEditText(boolean , editKv , it.dolg4!!)
                    }
                }
            }
        }
        binding.apply {
            editVodokanal.isEnabled = false
            editYtke.isEnabled = false
            editYzhtrans.isEnabled = false
            editKv.isEnabled = false
            editVodokanal.doBeforeTextChanged { text, start, count, after ->
                try {
                    serviceViewModel.minusTotalPay(text.toString().toDouble())
                }catch(e:java.lang.Exception){
                    serviceViewModel.minusTotalPay(0.0)
                }
            }
            editVodokanal.doAfterTextChanged {
                try {
                    serviceViewModel.plusTotalPay(it.toString().toDouble())
                }catch(e:java.lang.Exception){
                    serviceViewModel.plusTotalPay(0.0)
                }
            }
            editYtke.doBeforeTextChanged { text, start, count, after ->
                try {
                    serviceViewModel.minusTotalPay(text.toString().toDouble())
                }catch(e:java.lang.Exception){
                    serviceViewModel.minusTotalPay(0.0)
                }
            }
            editYtke.doAfterTextChanged {
                try {
                    serviceViewModel.plusTotalPay(it.toString().toDouble())
                }catch(e:java.lang.Exception){
                    serviceViewModel.plusTotalPay(0.0)
                }
            }
            editYzhtrans.doBeforeTextChanged { text, start, count, after ->
                try {
                    serviceViewModel.minusTotalPay(text.toString().toDouble())
                }catch(e:java.lang.Exception){
                    serviceViewModel.minusTotalPay(0.0)
                }
            }
            editYzhtrans.doAfterTextChanged {
                try {
                    serviceViewModel.plusTotalPay(it.toString().toDouble())
                }catch(e:java.lang.Exception){
                    serviceViewModel.plusTotalPay(0.0)
                }
            }
            editKv.doBeforeTextChanged { text, start, count, after ->
                try {
                    serviceViewModel.minusTotalPay(text.toString().toDouble())
                }catch(e:java.lang.Exception){
                    serviceViewModel.minusTotalPay(0.0)
                }
            }
            editKv.doAfterTextChanged {
                try {
                    serviceViewModel.plusTotalPay(it.toString().toDouble())
                }catch(e:java.lang.Exception){
                    serviceViewModel.plusTotalPay(0.0)
                }
            }
        }
    }

    private fun checkEditText(boolean: Boolean ,editText: EditText , double: Double){
        editText.isEnabled = boolean
        if(boolean) {
            editText.setText(double.toString())
        }else{
            editText.setText(0.00.toString())
        }
    }
}