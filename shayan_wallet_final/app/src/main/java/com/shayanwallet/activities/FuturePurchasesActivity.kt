package com.shayanwallet.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shayanwallet.R
import com.shayanwallet.adapters.PurchaseAdapter
import com.shayanwallet.db.DatabaseClient
import com.shayanwallet.models.Purchase
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FuturePurchasesActivity : AppCompatActivity() {

    private lateinit var purchasesRecyclerView: RecyclerView
    private lateinit var purchaseAdapter: PurchaseAdapter
    private var purchaseList: MutableList<Purchase> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_future_purchases)

        purchasesRecyclerView = findViewById(R.id.futurePurchasesRecyclerView)
        purchaseAdapter = PurchaseAdapter(purchaseList)
        purchasesRecyclerView.layoutManager = LinearLayoutManager(this)
        purchasesRecyclerView.adapter = purchaseAdapter

        loadFuturePurchases()
    }

    private fun loadFuturePurchases() {
        lifecycleScope.launch {
            val purchases = withContext(Dispatchers.IO) {
                DatabaseClient.getDatabase(this@FuturePurchasesActivity)
                    .purchaseDao()
                    .getAll()
            }
            purchaseList.clear()
            purchaseList.addAll(purchases)
            purchaseAdapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        loadFuturePurchases()
    }
}
