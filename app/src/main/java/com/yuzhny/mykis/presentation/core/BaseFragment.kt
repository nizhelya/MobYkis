package com.yuzhny.mykis.presentation.core

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.ActivityMainBinding
import com.yuzhny.mykis.domain.type.Failure


abstract class BaseFragment : Fragment() {
 private var _binding : ActivityMainBinding? = null
 private val binding get() = _binding!!
 open fun handleFailure(failure: Failure?) {
  when (failure) {
   is Failure.FlatAlreadyInDataBase -> showMessage(getString(R.string.error_flat_in_db))
   is Failure.NetworkConnectionError -> showMessage(getString(R.string.error_network))
   is Failure.ServerError -> showMessage(getString(R.string.error_server))
   is Failure.EmailAlreadyExistError -> showMessage(getString(R.string.error_email_already_exist))
   is Failure.AuthError -> showMessage(getString(R.string.error_auth))
//   is Failure.TokenError -> navigator.showLogin(this)
   is Failure.AlreadyFriendError -> showMessage(getString(R.string.error_already_friend))
   is Failure.AlreadyRequestedFriendError -> showMessage(getString(R.string.error_already_requested_friend))
   is Failure.FilePickError -> showMessage(getString(R.string.error_picking_file))
   is Failure.EmailNotRegisteredError -> showMessage(getString(R.string.email_not_registered))
   is Failure.CantSendEmailError -> showMessage(getString(R.string.error_cant_send_email))
  }
 }

 fun showMessage(message: String) {
  Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
 }
}
