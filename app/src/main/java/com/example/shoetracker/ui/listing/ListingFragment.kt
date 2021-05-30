package com.example.shoetracker.ui.listing

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoetracker.R
import com.example.shoetracker.data.models.Shoe
import com.example.shoetracker.databinding.FragmentListingBinding
import com.example.shoetracker.databinding.ShoeItemBinding
import com.example.shoetracker.ui.ShoeViewModel

/**
 * A [Fragment] subclass for the [Shoe][com.example.shoetracker.data.models.Shoe] listing screen.
 */
class ListingFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentListingBinding
    private lateinit var itemBinding: ShoeItemBinding

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
                .setLaunchSingleTop(true).setPopUpTo(R.id.nav_graph, true)
                .setEnterAnim(R.anim.nav_default_enter_anim)
                .setExitAnim(R.anim.nav_default_exit_anim)
                .build()
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
        val shoesLayout: LinearLayout = binding.linearLayoutShoes
        if (shoes.isNullOrEmpty()) {
            val noShoes = TextView(context)
            noShoes.textSize = 24.0F
            noShoes.gravity = 1
            noShoes.text = getString(R.string.listing_message_no_shoes_added)
            noShoes.setTextColor(ContextCompat.getColor(noShoes.context, R.color.black))
            noShoes.typeface = ResourcesCompat.getFont(noShoes.context, R.font.gudea_bold)
            shoesLayout.addView(noShoes)
        } else {
            for (shoe: Shoe in shoes) {
                itemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.shoe_item, null, false)
                itemBinding.shoe = shoe
                shoesLayout.addView(itemBinding.root)
            }
        }
    }
}