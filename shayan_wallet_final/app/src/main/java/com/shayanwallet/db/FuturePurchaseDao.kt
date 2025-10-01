package com.shayanwallet.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shayanwallet.models.FuturePurchase

@Dao
interface FuturePurchaseDao {
    @Insert
    suspend fun insert(futurePurchase: FuturePurchase)

    @Query("SELECT * FROM future_purchases")
    suspend fun getAll(): List<FuturePurchase>
}
