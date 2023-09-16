package com.calculator.evaluation_4.databaseBuilder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.calculator.evaluation_4.OfferDatabase.OfferDao
import com.calculator.evaluation_4.OfferDatabase.OfferEntity
import com.calculator.evaluation_4.otpDatabase.OtpVerificationStatusDao
import com.calculator.evaluation_4.otpDatabase.OtpVerificationStatusEntity

@Database(entities = [OfferEntity::class, OtpVerificationStatusEntity::class], version = 2, exportSchema = false)
abstract class OfferDatabase:RoomDatabase() {
    abstract fun offerDao(): OfferDao

    abstract fun otpVerificationStatusDao(): OtpVerificationStatusDao
    companion object{

        @Volatile
        private var Instance : OfferDatabase? = null

        fun getDataBaseDetails(context: Context): OfferDatabase {
            return Instance ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context, OfferDatabase::class.java,"OffersDatabase"
                ).allowMainThreadQueries().build()
                Instance = instance
                instance
            }
        }

    }
}
