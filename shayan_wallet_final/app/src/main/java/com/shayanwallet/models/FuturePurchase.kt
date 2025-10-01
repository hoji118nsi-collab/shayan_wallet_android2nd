package com.shayanwallet.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "future_purchases")
data class FuturePurchase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemName: String,
    val cost: Int,
    val purchaseDate: Date
)
