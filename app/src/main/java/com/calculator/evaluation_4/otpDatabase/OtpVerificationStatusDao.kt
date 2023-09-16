package com.calculator.evaluation_4.otpDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface OtpVerificationStatusDao {

    @Query("SELECT otp_verified FROM OtpVerificationStatus WHERE deviceId = 1")
    fun isOtpVerified(): LiveData<Boolean>

    @Upsert
    suspend fun updateOtpStatus(otpVerified: OtpVerificationStatusEntity)
}
