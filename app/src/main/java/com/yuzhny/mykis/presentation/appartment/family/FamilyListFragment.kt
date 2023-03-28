package com.yuzhny.mykis.presentation.appartment.family

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.databinding.FragmentFamilyListBinding
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notifyAll
import javax.inject.Inject

@AndroidEntryPoint
class FamilyListFragment  : BaseFragment() {

    private val familyViewModel : FamilyListViewModel by activityViewModels()
    private val listViewModel : AppartmentListViewModel by activityViewModels()

    private var _binding : FragmentFamilyListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var familyAdapter: FamilyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        familyViewModel.apply {
            onSuccess(family , ::handleFamily)
        }
        _binding = FragmentFamilyListBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.INVISIBLE
        familyViewModel.getFamily(listViewModel.currentAddress)
        binding.recyclerView.apply {
            itemAnimator = object : DefaultItemAnimator() {
                override fun onAnimationFinished(viewHolder: RecyclerView.ViewHolder) {
                    super.onAnimationFinished(viewHolder)
                    Log.d("family_fragment" , "onAnimationFinished")
                    binding.loadingView.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }
            adapter = familyAdapter
        }
    }
    private fun handleFamily(familyEntity: List<FamilyEntity>?){
        if(familyAdapter.currentList == familyEntity) {
            binding.loadingView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }else{
            familyAdapter.submitList(familyEntity)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("family_fragment"     , "onDestroyView")
    }
}