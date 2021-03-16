package com.example.shoetracker.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoetracker.data.models.Shoe

/**
 * A shared [ViewModel] subclass for creating and displaying [Shoe] data.
 */
class ShoeViewModel : ViewModel() {

    var manufacturer: String = ""
    var name: String = ""
    var size: Double? = null
    var description: String = ""
    private var shoes: MutableList<Shoe> = mutableListOf()

    private var _shoesLiveData = MutableLiveData<MutableList<Shoe>>()
    val shoesLiveData: LiveData<MutableList<Shoe>>
        get() = _shoesLiveData

    private var _eventOnSave = MutableLiveData<Boolean>()
    val eventOnSave: LiveData<Boolean>
        get() = _eventOnSave

    private var _eventOnCancel = MutableLiveData<Boolean>()
    val eventOnCancel: LiveData<Boolean>
        get() = _eventOnCancel

    init {
        _shoesLiveData.value = null
    }

    /**
     * Updates the shoes [list][shoes] and [live data][_shoesLiveData] and assigns **true** to the
     * [save event boolean][_eventOnSave] observed by the
     * [DetailsFragment][com.example.shoetracker.ui.details.DetailsFragment].
     * @param manufacturer A string that contains the shoe manufacturer.
     * @param name A string that contains the shoe name.
     * @param size A double that contains the shoe size.
     * @param description A string that contains the shoe description.
     */
    fun onClickSave(manufacturer: String, name: String, size: Double?, description: String) {
        _eventOnSave.value = true
        shoes.add(Shoe(manufacturer, name, size, description))
        _shoesLiveData.value = shoes
        resetFields()
    }

    /**
     * Clears previous shoe data from the fields to add new shoes.
     */
    private fun resetFields() {
        manufacturer = ""
        name = ""
        size = null
        description = ""
    }

    /**
     * Assigns **true** to the [cancel event boolean][_eventOnCancel] observed by the
     * [DetailsFragment][com.example.shoetracker.ui.details.DetailsFragment].
     */
    fun onClickCancel() {
        _eventOnCancel.value = true
    }

    /**
     * Resets the click event booleans to a value of false.
     */
    fun onClickComplete() {
        if (_eventOnSave.value == true) {
            _eventOnSave.value = false
        }
        if (_eventOnCancel.value == true) {
            _eventOnCancel.value = false
        }
    }
}