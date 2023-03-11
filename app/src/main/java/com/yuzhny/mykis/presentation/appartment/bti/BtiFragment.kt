package com.yuzhny.mykis.presentation.appartment.bti

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.databinding.EditDialogLayoutBinding
import com.yuzhny.mykis.databinding.FragmentBtiBinding
import com.yuzhny.mykis.databinding.FragmentFamilyListBinding
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.appartment.util.hideIfEmpty
import com.yuzhny.mykis.presentation.appartment.util.isEmptyFun
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BtiFragment : BaseFragment() {


    private var _binding : FragmentBtiBinding? = null
    private val binding get() = _binding!!


    private val  appartmentListViewModel: AppartmentListViewModel by activityViewModels()

    val dialogLayout = LayoutInflater.from(requireContext()).inflate(R.layout.edit_dialog_layout , null)
    val phoneEdit = dialogLayout.findViewById<EditText>(R.id.edit_phone_number)
    val emailEdit = dialogLayout.findViewById<EditText>(R.id.edit_email)

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
                hideIfEmpty(it.nanim ,  linearEmployer)
                phoneEdit.setText(it.phone)
                emailEdit.setText(it.email)
                editIcon.setOnClickListener {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(getString(R.string.dialog_edit_title))
                        .setCancelable(true)
                        .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                        }
                        .setPositiveButton(getString(R.string.edit)) { _, _ ->
                        }
                        .setView(dialogLayout)
                        .show()
                }
            }
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
    private fun showEditMailDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_edit_title))
            .setCancelable(true)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
            }
            .setPositiveButton(getString(R.string.edit)) { _, _ ->
            }
            .setView(LayoutInflater.from(requireContext()).inflate(R.layout.edit_dialog_layout , null))
            .show()
    }

}