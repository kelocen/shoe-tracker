package com.example.shoestoreinventory.utils

import androidx.databinding.InverseMethod

/**
 * A utility object for converting between [String] and [Double] for the
 * **size** property of the [Shoe][com.example.shoestoreinventory.data.models.Shoe] data class.
 */
object Converter {
    @InverseMethod("stringToDouble")
    @JvmStatic
            /**
             * Converts the shoe size from a double to a string.
             * @param size A [Double] that contains the shoe size.
             * @return A [String] with the shoe size.
             */
    fun doubleToString(size: Double): String {
        return size.toString()
    }

    @JvmStatic
            /**
             * Converts the shoe size from a string to a double.
             * @param size A [String] that contains the shoe size.
             * @return A [Double] with the shoe size.
             */
    fun stringToDouble(size: String): Double {
        if (size.isEmpty()) {
            return 0.0
        }
        return size.toDouble()
    }
}