package com.calculator.evaluation_4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation_4.databaseBuilder.OfferDatabase.Companion.getDataBaseDetails
import com.calculator.evaluation_4.OfferDatabase.OfferEntity
import com.calculator.evaluation_4.OfferRepository.OfferRepository
import com.calculator.evaluation_4.R
import com.calculator.evaluation_4.viewModel.OfferSharedViewModel
import com.calculator.evaluation_4.adapter.OfferAdapter
import com.calculator.evaluation_4.databinding.FragmentReBinding
import com.calculator.evaluation_4.viewModel.OfferModelFactory

class OffersFragment : Fragment() {
    private lateinit var binding: FragmentReBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OfferAdapter
    private lateinit var offerSharedViewModel: OfferSharedViewModel
    private lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = getDataBaseDetails(requireContext())
        val repository = OfferRepository(database.offerDao(), database.otpVerificationStatusDao())
        viewModelFactory = OfferModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        offerSharedViewModel = ViewModelProvider(this, viewModelFactory)[OfferSharedViewModel::class.java]

        binding.addButton.setOnClickListener {
            addItemToDatabase()
        }

        registerForContextMenu(binding.offerInputText)

        recyclerView = binding.recyclerview
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        val itemClickListener: (Int) -> Unit = { position ->
            offerSharedViewModel.toggleOnCardClick(position)
            adapter.notifyItemChanged(position)
        }

        offerSharedViewModel.offerSelectedButtonVisibility.observe(viewLifecycleOwner) { isAnyOfferSelected ->
            binding.buttonSuccessSelected.visibility = if (isAnyOfferSelected) View.VISIBLE else View.GONE
        }

        offerSharedViewModel.allOffers.observe(viewLifecycleOwner) { offers ->
            offerSharedViewModel.continueBtnVisibility(offers)
            adapter = OfferAdapter(requireContext(), offers, itemClickListener)
            recyclerView.adapter = adapter
        }

        binding.buttonSuccessSelected.setOnClickListener {
            navigateToSelectedFragment()
        }
    }


    private fun addItemToDatabase() {
        val offerName = binding.offerInputText.text.toString()
        if (offerName.isNotEmpty()) {
            val offer = OfferEntity(offerName = offerName, isSelected = false)
            offerSharedViewModel.addOffer(offer)
            offerSharedViewModel.hideKeyboard(binding.addButton)
            binding.offerInputText.text.clear()
        }
    }

    private fun navigateToSelectedFragment() {
        view?.findNavController()?.navigate(R.id.action_reFragment_to_selectedFragment)
    }
}











