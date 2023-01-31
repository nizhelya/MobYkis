package com.yuzhny.mykis

import android.app.Application
import com.yuzhny.mykis.data.cache.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication:Application()
