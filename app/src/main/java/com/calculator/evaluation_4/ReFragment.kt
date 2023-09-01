package com.calculator.evaluation_4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation2_recyclerview.adopter.CustomAdopter
import com.calculator.evaluation2_recyclerview.dataView.DataView
import com.calculator.evaluation2_recyclerview.dataModel.DataModel
import com.calculator.evaluation_4.databinding.FragmentOtpSuccessfulBinding
import com.calculator.evaluation_4.databinding.FragmentReBinding

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
        val offerSelectedBtn = binding.buttonSuccessSelected;

        val itemClickListener: (Int) -> Unit = { position ->

            val selectedOffer = dataView.filter { it.isSelected == true }.size
            offerSelectedBtn.visibility = if(selectedOffer>0) View.VISIBLE else View.GONE

            // Find the clicked CardView in the RecyclerView
            val clickedCardView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
            val card = clickedCardView?.findViewById<CardView>(R.id.offerId)
            val chkImg = clickedCardView?.findViewById<ImageView>(R.id.checkImg)

            card?.setCardBackgroundColor(
                if (card?.cardBackgroundColor?.defaultColor == ContextCompat.getColor(requireContext(), R.color.default_card_color))
                    ContextCompat.getColor(requireContext(), android.R.color.white)
                else
                    ContextCompat.getColor(requireContext(), R.color.default_card_color)
            )
            chkImg?.visibility = if (chkImg?.visibility == View.GONE) View.VISIBLE else View.GONE

        }


        adaptor = CustomAdopter(requireContext(), dataView, itemClickListener)
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

