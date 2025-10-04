package com.shayanwallet.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.shayanwallet.models.Transaction
import com.shayanwallet.models.Investment
import com.shayanwallet.models.FuturePurchase
import com.shayanwallet.models.Purchase

@Database(
    entities = [Transaction::class, Investment::class, FuturePurchase::class, Purchase::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun investmentDao(): InvestmentDao
    abstract fun futurePurchaseDao(): FuturePurchaseDao
    abstract fun purchaseDao(): PurchaseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "shayan_wallet_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
