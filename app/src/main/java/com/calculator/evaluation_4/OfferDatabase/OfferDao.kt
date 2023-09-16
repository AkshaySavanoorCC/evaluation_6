package com.calculator.evaluation_4.OfferDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface OfferDao {
    @Insert
    suspend fun insertOffer(vararg offers:OfferEntity)

    @Query("SELECT * FROM offers")
    fun getAllOffers(): LiveData<List<OfferEntity>>

    @Update
    suspend fun updateOfferOnToggle(offer: OfferEntity)

    @Query("SELECT * FROM offers WHERE offer_selected = 1")
    fun getAllSelectedOffers(): LiveData<List<OfferEntity>>

}