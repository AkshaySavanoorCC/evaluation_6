package com.calculator.evaluation_4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation_4.databaseBuilder.OfferDatabase.Companion.getDataBaseDetails
import com.calculator.evaluation_4.OfferRepository.OfferRepository
import com.calculator.evaluation_4.adapter.SelectedOfferAdapter
import com.calculator.evaluation_4.viewModel.OfferSharedViewModel
import com.calculator.evaluation_4.databinding.FragmentSelectedBinding
import com.calculator.evaluation_4.viewModel.OfferModelFactory


class OffersSelectedFragment : Fragment() {
    private lateinit var binding: FragmentSelectedBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SelectedOfferAdapter
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
        binding = FragmentSelectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpUI()
    }
    private fun setUpUI(){
        offerSharedViewModel = ViewModelProvider(this, viewModelFactory)[OfferSharedViewModel::class.java]
        offerSharedViewModel.selectedOffers.observe(viewLifecycleOwner, Observer { selectedOffers ->
            adapter = SelectedOfferAdapter(requireContext(), selectedOffers)
            recyclerView.adapter = adapter
        })
    }
    private fun setUpRecyclerView(){
        recyclerView = binding.recyclerview
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

    }

}


