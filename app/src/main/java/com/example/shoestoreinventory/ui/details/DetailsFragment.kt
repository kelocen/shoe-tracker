package com.example.shoestoreinventory.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.R
import com.example.shoestoreinventory.databinding.FragmentDetailsBinding
import com.example.shoestoreinventory.ui.ShoeViewModel

/**
 * A [Fragment] subclass responsible for adding new [Shoes][com.example.shoestoreinventory.data.models.Shoe].
 */
class DetailsFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shoeViewModel = shoeViewModel
        shoeViewModel.eventOnSave.observe(viewLifecycleOwner, { saveClicked ->
            if (saveClicked) {
                findNavController().navigateUp()
                shoeViewModel.onClickComplete()
            }
        })
        shoeViewModel.eventOnCancel.observe(viewLifecycleOwner, { cancelClicked ->
            if (cancelClicked) {
                findNavController().navigateUp()
                shoeViewModel.onClickComplete()
            }
        })
    }
}