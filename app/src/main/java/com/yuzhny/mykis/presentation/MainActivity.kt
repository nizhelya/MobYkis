package com.yuzhny.mykis.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yuzhny.mykis.R
import com.yuzhny.mykis.cache.database.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}