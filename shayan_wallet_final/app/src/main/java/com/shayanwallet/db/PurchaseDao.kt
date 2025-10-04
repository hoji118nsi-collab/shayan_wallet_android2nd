package com.shayanwallet.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shayanwallet.models.Purchase

@Dao
interface PurchaseDao {

    @Insert
    suspend fun insert(purchase: Purchase)

    @Query("SELECT * FROM purchases ORDER BY date ASC")
    suspend fun getAll(): List<Purchase>
}
