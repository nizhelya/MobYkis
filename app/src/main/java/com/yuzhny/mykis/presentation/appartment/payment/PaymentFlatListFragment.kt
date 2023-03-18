package com.yuzhny.mykis.presentation.appartment.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentPaymentFlatListBinding
import com.yuzhny.mykis.databinding.FragmentServiceListBinding
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment

class PaymentFlatListFragment : BaseFragment() {
    private val listViewModel: AppartmentListViewModel by activityViewModels()
    private val paymentListViewModel : PaymentListViewModel by activityViewModels()
    private var _binding: FragmentPaymentFlatListBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentFlatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paymentListViewModel.getFlatPayments(listViewModel.currentAddress)
    }

}