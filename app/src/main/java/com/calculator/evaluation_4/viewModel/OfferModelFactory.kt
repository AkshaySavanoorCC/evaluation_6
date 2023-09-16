package com.calculator.evaluation_4.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.calculator.evaluation_4.OfferRepository.OfferRepository

class OfferModelFactory(private val repository: OfferRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfferSharedViewModel::class.java)){
            return OfferSharedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}