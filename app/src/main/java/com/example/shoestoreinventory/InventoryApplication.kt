@file:Suppress("unused")

package com.example.shoestoreinventory

import android.app.Application
import timber.log.Timber

class InventoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}