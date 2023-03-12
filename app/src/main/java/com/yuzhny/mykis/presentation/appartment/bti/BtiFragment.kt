package com.yuzhny.mykis.presentation.appartment.bti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.databinding.FragmentBtiBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.appartment.util.hideIfEmpty
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_bti.*

@AndroidEntryPoint
class BtiFragment : BaseFragment() {


    private var _binding : FragmentBtiBinding? = null
    private val binding get() = _binding!!


    private val  appartmentListViewModel: AppartmentListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBtiBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appartmentListViewModel.apply {
            onSuccess(resultText, ::handleResultText)
            onSuccess(appartments, ::handleAppartments)
            onFailure(failureData, ::handleFailure)
        }
        appartmentListViewModel.appartment.observe(this.viewLifecycleOwner){
            binding.apply {
                fullArea.text = it.areaFull.toString()
                lifeArea.text = it.areaLife.toString()
                extraArea.text = it.areaDop.toString()
                balconyArea.text = it.areaBalk.toString()
                elevator.isChecked = trueOrFalse(it.lift)
                nanim.text = it.nanim
                fio.text = it.fio
//                if(isEmptyFun(it.phone)){
//                    phone.text = getString(R.string.empty_phone)
//                }else
                    phone.text = it.phone
//                if(isEmptyFun(it.email)){
//                    email.text = getString(R.string.empty_email)
//                }else
                email.text = it.email
                privateCheck.isChecked = trueOrFalse(it.privat)
                dataOrder.text = it.dataOrder
                order.text = it.order
                hideIfEmpty(it.fio , linearOwner)
            }

            binding.editIcon.setOnClickListener{ _ ->
                showEditContactsDialog(it.addressId , it.phone , it.email)
            }

        }
    }
    private fun handleAppartment(appartmentEntity: AppartmentEntity?){
        appartmentEntity?.let {
            appartmentListViewModel.getFlatById(it.addressId)
        }
    }
    private fun handleResultText(getSimpleResponse: GetSimpleResponse?) {
        getSimpleResponse?.let {
            if(it.success == 1){
                it.success = 0
                Toast.makeText(requireContext() ,getString(R.string.updated), Toast.LENGTH_SHORT ).show()
                appartmentListViewModel.getAppartmentsByUser()
            }
        }
    }
    private fun showEditContactsDialog(addressId: Int, phoneText:String, emailText:String ) {
        val dialogLayout  = layoutInflater.inflate(R.layout.edit_dialog_layout , null)
        val editPhone :TextView = dialogLayout.findViewById(R.id.edit_phone_number)
        val editEmail :TextView = dialogLayout.findViewById(R.id.edit_email)
        editPhone.text = phoneText
        editEmail.text = emailText
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_edit_title))
            .setCancelable(true)
            .setIcon(R.drawable.ic_edit)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->

            }
            .setPositiveButton(getString(R.string.edit)) { _, _ ->
                appartmentListViewModel.updateBti(addressId , editPhone.text.toString() ,editEmail.text.toString())
            }
            .setView(dialogLayout)
            .show()
    }
    private fun handleAppartments(appartmentEntity:  List<AppartmentEntity>?) {
        if (appartmentEntity != null && appartmentEntity.isNotEmpty()) {
            appartmentListViewModel.getFlatFromCache(appartmentListViewModel.currentAddress)
        }
    }
}