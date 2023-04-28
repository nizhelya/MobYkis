package com.yuzhny.mykis.presentation.appartment.payment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.databinding.FragmentPaymentFlatListBinding
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaymentFlatListFragment : BaseFragment() {
    private val listViewModel: AppartmentListViewModel by activityViewModels()
    private val paymentListViewModel : PaymentListViewModel by activityViewModels()
    private var _binding: FragmentPaymentFlatListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var paymentListAdapter : PaymentListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        paymentListViewModel.apply {
            onSuccess(paymentsFlat ,::handlePayments)
            onFailure(failureData, ::handleFailure)
        }
        _binding = FragmentPaymentFlatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        paymentListViewModel.getFlatPayments(listViewModel.currentAddress)
        paymentListViewModel.paymentItem.observe(this.viewLifecycleOwner){
            i -> i?.let {
                paymentListAdapter.submitList(it)
            }
        }
        binding.recyclerView.adapter = paymentListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        paymentListViewModel.clearPaymentList()
    }
    private fun handlePayments(paymentEntity:  List<PaymentEntity>?) {
        if (paymentEntity != null && paymentEntity.isNotEmpty()) {
           paymentListViewModel.getPaymentItem(listViewModel.currentAddress , binding.loadingView , binding.recyclerView)
        }
    }

}