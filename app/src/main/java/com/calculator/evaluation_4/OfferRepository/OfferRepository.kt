package com.calculator.evaluation_4.OfferRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calculator.evaluation_4.OfferDatabase.OfferDao
import com.calculator.evaluation_4.OfferDatabase.OfferEntity
import com.calculator.evaluation_4.otpDatabase.OtpVerificationStatusDao
import com.calculator.evaluation_4.otpDatabase.OtpVerificationStatusEntity

class OfferRepository(private val offerDao :OfferDao,private val otpVerificationStatusDao: OtpVerificationStatusDao) {
    val allOffers: LiveData<List<OfferEntity>> = offerDao.getAllOffers()
    val isOtpVerified: LiveData<Boolean> = otpVerificationStatusDao.isOtpVerified()

    suspend fun insertOffer(offer: OfferEntity) {
        offerDao.insertOffer(offer)
    }

    fun allSelectedOffers(): LiveData<List<OfferEntity>> = offerDao.getAllSelectedOffers()

    suspend fun updateOffer(offer: OfferEntity) {
        offerDao.updateOfferOnToggle(offer)
    }
    suspend fun updateOtpVerified(otpVerified: OtpVerificationStatusEntity) {
        otpVerificationStatusDao.updateOtpStatus(otpVerified)
    }
}
