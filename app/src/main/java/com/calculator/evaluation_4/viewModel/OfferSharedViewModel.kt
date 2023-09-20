package com.calculator.evaluation_4.viewModel

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calculator.evaluation_4.OfferDatabase.OfferEntity
import com.calculator.evaluation_4.otpDatabase.OtpVerificationStatusEntity
import com.calculator.evaluation_4.OfferRepository.OfferRepository

import kotlinx.coroutines.launch

class OfferSharedViewModel(private val repository: OfferRepository) : ViewModel() {

    val allOffers: LiveData<List<OfferEntity>> = repository.allOffers
    private val allSelectedOffers : LiveData<List<OfferEntity>> = repository.allSelectedOffers()

    private val _selectedOffers: MutableLiveData<List<OfferEntity>> = MutableLiveData()
    val selectedOffers: LiveData<List<OfferEntity>> = _selectedOffers

    private val _offerSelectedButtonVisibility = MutableLiveData<Boolean>()
    val offerSelectedButtonVisibility: LiveData<Boolean> = _offerSelectedButtonVisibility


    val isVerified = repository.isOtpVerified


    init {
        allSelectedOffers.observeForever { selectedOffersList ->
            _selectedOffers.value = selectedOffersList
        }
    }


    fun updateOtpStatus(){
        viewModelScope.launch {
            repository.updateOtpVerified(OtpVerificationStatusEntity(1,true))

        }
    }

    fun addOffer(offer: OfferEntity) {
        viewModelScope.launch {
            repository.insertOffer(offer)
        }
    }



    fun continueBtnVisibility(offers: List<OfferEntity>) {
        val selectedOfferListSize = offers.filter { it.isSelected }.size
        _offerSelectedButtonVisibility.value = selectedOfferListSize > 0
    }

    fun toggleOnCardClick(position: Int) {
        val offers = requireNotNull(allOffers.value)
        if (position in offers.indices) {
            val offerList = offers.toMutableList()
            val selectedOffer = offerList[position]
            selectedOffer.isSelected = !selectedOffer.isSelected
            _selectedOffers.value = offerList
            viewModelScope.launch {
                repository.updateOffer(selectedOffer)
            }
        }
    }

    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
