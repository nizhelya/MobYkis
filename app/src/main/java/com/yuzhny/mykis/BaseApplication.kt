package com.yuzhny.mykis

import android.app.Application
import com.yuzhny.mykis.cache.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication:Application()
