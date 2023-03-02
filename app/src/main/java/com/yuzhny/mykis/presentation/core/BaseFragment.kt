package com.yuzhny.mykis.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yuzhny.mykis.domain.type.Failure
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


//abstract class BaseFragment<VB : ViewBinding> : Fragment() {
//
//    var _binding: VB? = null
//    val binding get() = _binding!!
//
//
//    fun handleFailure(failure: Failure?) = base { handleFailure(failure) }
//
//    fun showMessage(message: String) = base { showMessage(message) }
//
//
//        inline fun base(block: BaseActivity.() -> Unit) {
//        activity.base(block)
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        _binding = getViewBinding(view)
//    }
//    abstract fun getViewBinding(view: View): VB
//
//    protected fun setOnItemClickListener(func: (Any?, View) -> Unit) {
//        viewAdapter.setOnClick(func)
//    }
//
//
//}
