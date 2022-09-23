package com.stone.movieticket.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stone.movieticket.data.vos.PaymentVO

@Dao
interface PaymentMethodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaymentMethodList(paymentMethodList: List<PaymentVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSinglePaymentMethod(paymentMethod: PaymentVO)

    @Query("SELECT * FROM paymentMethod")
    fun getAllPaymentMethods(): List<PaymentVO>

    @Query("DELETE FROM paymentMethod")
    fun deleteAllPaymentMethods()

}