package com.example.shoetracker.ui.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoetracker.R
import com.example.shoetracker.databinding.FragmentStepOneBinding

/**
 * A [Fragment] subclass for the instructions screen.
 */
class StepOneFragment : Fragment() {

    private lateinit var binding: FragmentStepOneBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_one, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonContinue.setOnClickListener {
            findNavController().navigate(R.id.action_StepOneFragment_to_StepTwoFragment)
        }
    }
}