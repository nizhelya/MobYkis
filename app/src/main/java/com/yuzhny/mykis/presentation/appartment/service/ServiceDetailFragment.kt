package com.yuzhny.mykis.presentation.appartment.service

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemAnimatorFinishedListener
import com.yuzhny.mykis.databinding.FragmentServiceDetailBinding
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.presentation.MainActivity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServiceDetailFragment @Inject constructor() : BaseFragment() {

    private val serviceViewModel: ServiceViewModel by activityViewModels()
    private val listViewModel: AppartmentListViewModel by activityViewModels()

    private var _binding: FragmentServiceDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var serviceAdapter: ServiceDetailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        serviceViewModel.apply {
            onSuccess(serviceDetail , ::handleServices)
        }
        _binding = FragmentServiceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.INVISIBLE
        serviceViewModel.getDetailService(
            listViewModel.currentAddress,
            listViewModel.currentHouse,
            serviceViewModel.currentService,
            0,
            1,
        )
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(true)
        actionBar.title = serviceViewModel.currentServiceTitle
        binding.recyclerView.apply {
            itemAnimator = object : DefaultItemAnimator() {
                override fun onAnimationFinished(viewHolder: RecyclerView.ViewHolder) {
                    super.onAnimationFinished(viewHolder)
                    scrollToPosition(0)
                    binding.loadingView.visibility = View.GONE
                    visibility = View.VISIBLE
                    Log.d("service_fragment" , "onAnimationFinished")
                }
            }
            adapter = serviceAdapter
        }
    }
    private fun handleServices(serviceEntity: List<ServiceEntity>?){
        if(serviceAdapter.currentList == serviceEntity){
            binding.loadingView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }else{
            serviceAdapter.submitList(serviceEntity)
        }
    }
}