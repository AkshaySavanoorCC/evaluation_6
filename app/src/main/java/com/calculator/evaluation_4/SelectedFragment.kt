package com.calculator.evaluation_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation_4.adopter.SelectedAdopter
import com.calculator.evaluation_4.dataView.DataView
import com.calculator.evaluation_4.databinding.FragmentSelectedBinding

class SelectedFragment : Fragment() {
    private lateinit var binding: FragmentSelectedBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptor: SelectedAdopter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerview
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        val dataView = DataView().loadOffers().filter { it.isSelected == true }

        adaptor = SelectedAdopter(requireContext(), dataView)
        recyclerView.adapter = adaptor

        val dropDown = binding.dropdownMenu

        val list = listOf(
            getString(R.string.location_mangalore),
            getString(R.string.location_bangalore),
            getString(R.string.location_delhi)
        )


        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, list)

        binding.autoCompleteText.setAdapter(arrayAdapter)

    }
}

