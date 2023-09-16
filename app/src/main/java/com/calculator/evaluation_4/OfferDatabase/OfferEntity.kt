package com.calculator.evaluation_4.OfferDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Offers")
data class OfferEntity(
    @ColumnInfo(name = "otp_verified")
    val otpVerified : Boolean= false,

    @ColumnInfo(name = "offer_name")
    val offerName: String,

    @ColumnInfo(name = "offer_selected")
    var isSelected: Boolean,

) {
    @PrimaryKey(autoGenerate = true)
    var userid:Int? = null

}