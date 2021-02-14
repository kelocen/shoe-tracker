package com.example.shoestoreinventory.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
/**
 * A data class used to create Shoe objects.
 */
data class Shoe(var manufacturer: String, var name: String, var size: Double?,
                var description: String, val images: List<String> = mutableListOf()) : Parcelable