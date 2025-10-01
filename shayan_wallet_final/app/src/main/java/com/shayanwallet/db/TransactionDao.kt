package com.shayanwallet.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shayanwallet.models.Transaction

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<Transaction>
}
