package com.calculator.evaluation_4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation2_recyclerview.adopter.CustomAdopter
import com.calculator.evaluation2_recyclerview.dataView.DataView
import com.calculator.evaluation2_recyclerview.dataModel.DataModel
import com.calculator.evaluation_4.databinding.FragmentOtpSuccessfulBinding
import com.calculator.evaluation_4.databinding.FragmentReBinding
import com.calculator.evaluation_4.databinding.FragmentSelectedBinding

class SelectedFragment : Fragment() {
    private lateinit var binding: FragmentSelectedBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptor: CustomAdopter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Initialize views within the fragment's layout
        recyclerView = binding.recyclerview
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        // Sample data (replace with your own data)
        val dataView = DataView().loadOffers().filter { it.isSelected == true }

        // Item click listener
        val itemClickListener: (Int) -> Unit = { position ->


            // Find the clicked CardView in the RecyclerView
            val clickedCardView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
            val card = clickedCardView?.findViewById<CardView>(R.id.offerId)
            val chkImg = clickedCardView?.findViewById<ImageView>(R.id.checkImg)

            // Change background color to white and make ImageView visible
            card?.setCardBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            chkImg?.visibility = View.VISIBLE
        }

        // Initialize the adapter with the click listener
        adaptor = CustomAdopter(requireContext(), dataView, itemClickListener)
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

