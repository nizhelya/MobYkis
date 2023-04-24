package com.yuzhny.mykis.presentation.core

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.ActivityMainBinding
import com.yuzhny.mykis.domain.type.Failure


abstract class BaseFragment : Fragment() {
 open fun handleFailure(failure: Failure?) {
  when (failure) {
   is Failure.FailUpdateBti -> showMessage(getString(R.string.error_update))
   is Failure.FailAddReading -> showMessage(getString(R.string.error_add_reading))
   is Failure.FailIncorrectReading -> showMessage(getString(R.string.error_incorrect_reading))
   is Failure.MissingFields -> showMessage(getString(R.string.error_missing_fields))
   is Failure.FailDeleteFlat -> showMessage(getString(R.string.error_delete_flat))
   is Failure.IncorrectCode -> showMessage(getString(R.string.error_incorrect_code))
   is Failure.FlatAlreadyInDataBase -> showMessage(getString(R.string.error_flat_in_db))
   is Failure.NetworkConnectionError -> showMessage(getString(R.string.error_network))
   is Failure.ServerError -> showMessage(getString(R.string.error_server))
   is Failure.CantSendEmailError -> showMessage(getString(R.string.error_cant_send_email))
  }
 }

 private fun showMessage(message: String) {
  Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
 }
}
