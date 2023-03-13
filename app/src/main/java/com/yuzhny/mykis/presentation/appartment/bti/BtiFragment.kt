package com.yuzhny.mykis.presentation.appartment.bti

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
import com.yuzhny.mykis.databinding.FragmentBtiBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BtiFragment : BaseFragment() {


    private var _binding: FragmentBtiBinding? = null
    private val binding get() = _binding!!


    private val appartmentListViewModel: AppartmentListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBtiBinding.inflate(inflater,container,false)
//        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bti, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = appartmentListViewModel
            btiFragment = this@BtiFragment

        }

        binding.editIcon.setOnClickListener {
            showEditContactsDialog()
        }
        appartmentListViewModel.apply {
            onSuccess(resultText, ::handleResultText)
            onSuccess(appartment, ::handleAppartment)
            onFailure(failureData, ::handleFailure)
        }

    }

    private fun handleAppartment(appartmentEntity: AppartmentEntity?) {
        appartmentEntity?.let {
            appartmentListViewModel.getFlatById(it.addressId)
        }
    }

    private fun handleResultText(getSimpleResponse: GetSimpleResponse?) {
        getSimpleResponse?.let {
            if (it.success == 1) {
                it.success = 0
                Toast.makeText(requireContext(), getString(R.string.updated), Toast.LENGTH_SHORT)
                    .show()
//                appartmentListViewModel.apply {
//                    onSuccess(appartments, ::handleAppartment)
//                }

            }
        }
    }

    private fun showEditContactsDialog() {
        val dialogLayout = layoutInflater.inflate(R.layout.edit_dialog_layout, null)
        val editPhone: TextView = dialogLayout.findViewById(R.id.edit_phone_number)
        val editEmail: TextView = dialogLayout.findViewById(R.id.edit_email)
        editPhone.text = binding.phone.text.toString()
        editEmail.text = binding.email.text.toString()
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_edit_title))
            .setCancelable(true)
            .setIcon(R.drawable.ic_edit)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->

            }
            .setPositiveButton(getString(R.string.edit)) { _, _ ->
                appartmentListViewModel.updateBti(
                    8256,
                    editPhone.text.toString(),
                    editEmail.text.toString()
                )
            }
            .setView(dialogLayout)
            .show()
    }
}