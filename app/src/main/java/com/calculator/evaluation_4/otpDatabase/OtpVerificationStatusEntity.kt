package com.calculator.evaluation_4.otpDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OtpVerificationStatus")
data class OtpVerificationStatusEntity(
    @PrimaryKey
    val deviceId: Int = 1,
    @ColumnInfo(name = "otp_verified")
    val otpVerified: Boolean = false
){

}
