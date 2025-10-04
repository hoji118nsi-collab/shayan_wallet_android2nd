package com.shayanwallet.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shayanwallet.R
import com.shayanwallet.adapters.PurchaseAdapter
import com.shayanwallet.db.DatabaseClient
import com.shayanwallet.models.Purchase
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FuturePurchasesActivity : AppCompatActivity() {

    private lateinit var purchasesRecyclerView: RecyclerView
    private lateinit var purchaseAdapter: PurchaseAdapter
    private var purchaseList: MutableList<Purchase> = mutableListOf()
    private lateinit var addPurchaseBtn: Button

    private val addPurchaseLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            loadFuturePurchases() // فقط وقتی خرید جدید اضافه شد لیست را بروزرسانی کن
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_future_purchases)

        purchasesRecyclerView = findViewById(R.id.futurePurchasesRecyclerView)
        addPurchaseBtn = findViewById(R.id.addPurchaseBtn) // دکمه جدید

        purchaseAdapter = PurchaseAdapter(purchaseList)
        purchasesRecyclerView.layoutManager = LinearLayoutManager(this)
        purchasesRecyclerView.adapter = purchaseAdapter

        addPurchaseBtn.setOnClickListener {
            openAddPurchaseActivity()
        }

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

    private fun openAddPurchaseActivity() {
        val intent = Intent(this, AddPurchaseActivity::class.java)
        addPurchaseLauncher.launch(intent)
    }
}
