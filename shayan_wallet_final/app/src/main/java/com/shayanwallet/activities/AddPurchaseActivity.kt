package com.shayanwallet.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.shayanwallet.R
import com.shayanwallet.db.DatabaseClient
import com.shayanwallet.models.Purchase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddPurchaseActivity : AppCompatActivity() {

    private lateinit var itemNameEditText: EditText
    private lateinit var costEditText: EditText
    private lateinit var savePurchaseBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_purchase)

        itemNameEditText = findViewById(R.id.itemNameEditText)
        costEditText = findViewById(R.id.costEditText)
        savePurchaseBtn = findViewById(R.id.savePurchaseBtn)

        savePurchaseBtn.setOnClickListener {
            savePurchase()
        }
    }

    private fun savePurchase() {
        val itemName = itemNameEditText.text.toString()
        val cost = costEditText.text.toString().toIntOrNull() ?: 0
        val purchase = Purchase(title = itemName, amount = cost, date = Date())

        CoroutineScope(Dispatchers.IO).launch {
            DatabaseClient.getDatabase(this@AddPurchaseActivity)
                .purchaseDao()
                .insert(purchase)

            // بعد از ذخیره، نتیجه موفقیت‌آمیز برگردان
            runOnUiThread {
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}
