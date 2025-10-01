package com.shayanwallet.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shayanwallet.models.Investment

@Dao
interface InvestmentDao {
    @Insert
    suspend fun insert(investment: Investment)

    @Query("SELECT * FROM investments")
    suspend fun getAll(): List<Investment>
}
