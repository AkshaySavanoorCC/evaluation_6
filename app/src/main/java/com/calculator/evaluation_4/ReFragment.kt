package com.calculator.evaluation_4


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation_4.adopter.CustomAdopter
import com.calculator.evaluation_4.dataView.DataView
import com.calculator.evaluation_4.databinding.FragmentReBinding
import com.google.android.material.card.MaterialCardView

class ReFragment : Fragment() {
    private lateinit var binding: FragmentReBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptor: CustomAdopter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerview
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        val dataView = DataView().loadOffers()
        val offerSelectedBtn = binding.buttonSuccessSelected

        val itemClickListener: (Int) -> Unit = { position ->


            val selectedOffer = dataView.filter { it.isSelected == true }.size
            offerSelectedBtn.visibility = if (selectedOffer > 0) View.VISIBLE else View.GONE
            // Find the clicked CardView in the RecyclerView
            val clickedCardView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
            val cardView = clickedCardView?.findViewById<MaterialCardView>(R.id.offerId)

            if (cardView != null) {

                if (offerSelectedBtn.visibility == View.VISIBLE) {
                    cardView.strokeColor = Color.parseColor("#EE8664")
                    cardView.strokeWidth = resources.getDimensionPixelSize(R.dimen.stroke_width)

                } else {
                    cardView.strokeColor = Color.TRANSPARENT
                    cardView.strokeWidth = 0
                }
            }

            val chkImg = clickedCardView?.findViewById<ImageView>(R.id.checkImg)


            chkImg?.visibility = if (chkImg?.visibility == View.GONE) View.VISIBLE else View.GONE

        }

        adaptor = CustomAdopter(requireContext(), dataView, itemClickListener,offerSelectedBtn)
        recyclerView.adapter = adaptor

        val list = listOf(
            getString(R.string.location_mangalore),
            getString(R.string.location_bangalore),
            getString(R.string.location_delhi)
        )

        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, list)
        binding.autoCompleteText.setAdapter(arrayAdapter)

        binding.buttonSuccessSelected.setOnClickListener {
            it.findNavController().navigate(R.id.action_reFragment_to_selectedFragment)
        }
    }


}









