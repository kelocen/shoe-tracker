package com.example.shoestoreinventory.ui.listing

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoestoreinventory.R
import com.example.shoestoreinventory.data.models.Shoe
import com.example.shoestoreinventory.databinding.FragmentListingBinding
import com.example.shoestoreinventory.ui.ShoeViewModel

/**
 * A [Fragment] subclass for the [Shoe][com.example.shoestoreinventory.data.models.Shoe] listing screen.
 */
class ListingFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentListingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_logout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.fragment_LoginFragment) {
            val options = NavOptions.Builder()
                .setLaunchSingleTop(true).setPopUpTo(R.id.nav_graph, true).build()
            try {
                findNavController().navigate(item.itemId, null, options)
                true
            } catch (ex: IllegalArgumentException) {
                false
            }
        } else {
            NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                    || super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAddShoes.setOnClickListener {
            findNavController().navigate(R.id.action_ListingFragment_to_DetailsFragment)
        }
        shoeViewModel.shoesLiveData.observe(viewLifecycleOwner, { newShoes ->
            displayShoeList(newShoes)
        })
    }

    private fun displayShoeList(shoes: MutableList<Shoe>?) {
        // TODO replace with item layout
        val shoesLayout: LinearLayout = binding.layoutShoes
        val shoeManufacturerLabel = TextView(context)
        shoeManufacturerLabel.setText(R.string.label_shoe_manufacturer)
        val shoeNameLabel = TextView(context)
        shoeNameLabel.setText(R.string.label_shoe_name)
        val shoeSizeLabel = TextView(context)
        shoeSizeLabel.setText(R.string.label_shoe_size)
        val shoeDescriptionLabel = TextView(context)
        shoeDescriptionLabel.setText(R.string.label_shoe_description)
        val shoeManufacturer = TextView(context)
        val shoeName = TextView(context)
        val shoeSize = TextView(context)
        val shoeDescription = TextView(context)

        if (shoes.isNullOrEmpty()) {
            val noShoes = TextView(context)
            noShoes.text = "No shoes have been entered!"
            shoesLayout.addView(noShoes)
        } else {
            shoeManufacturer.text = shoes[0].manufacturer
            shoeName.text = shoes[0].name
            shoeSize.text = shoes[0].size.toString()
            shoeDescription.text = shoes[0].description

            shoesLayout.addView(shoeManufacturerLabel)
            shoesLayout.addView(shoeManufacturer)
            shoesLayout.addView(shoeNameLabel)
            shoesLayout.addView(shoeName)
            shoesLayout.addView(shoeSizeLabel)
            shoesLayout.addView(shoeSize)
            shoesLayout.addView(shoeDescriptionLabel)
            shoesLayout.addView(shoeDescription)
        }
    }
}