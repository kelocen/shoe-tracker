package com.example.shoestoreinventory.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.R
import com.example.shoestoreinventory.databinding.FragmentDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener {
            findNavController().navigate(R.id.action_save_DetailsFragment_to_ListingFragment)
        }
        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_cancel_DetailsFragment_to_ListingFragment)
        }
    }
}