package com.yuzhny.mykis.presentation.core

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yuzhny.mykis.domain.type.Failure

abstract class BaseActivity : AppCompatActivity() {
    open fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.FlatAlreadyInDataBase -> showMessage("Ви вже додали цю квартиру")
            else -> showMessage("Помилка")
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
inline fun Activity?.base(block: BaseActivity.() -> Unit) {
    (this as? BaseActivity)?.let(block)
}