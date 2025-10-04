package com.shayanwallet.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.shayanwallet.R
import com.shayanwallet.db.DatabaseClient
import com.shayanwallet.models.FuturePurchase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddPurchaseActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var amountEditText: EditText
    private lateinit var savePurchaseBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_purchase)

        titleEditText = findViewById(R.id.titleEditText)
        amountEditText = findViewById(R.id.amountEditText)
        savePurchaseBtn = findViewById(R.id.savePurchaseBtn)

        savePurchaseBtn.setOnClickListener {
            savePurchase()
        }
    }

    private fun savePurchase() {
        val title = titleEditText.text.toString()
        val amount = amountEditText.text.toString().toIntOrNull() ?: 0
        val purchase = FuturePurchase(title = title, amount = amount, date = Date())

        // ذخیره در دیتابیس با coroutine
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseClient.getDatabase(this@AddPurchaseActivity).futurePurchaseDao().insert(purchase)
            runOnUiThread {
                finish() // برگرد به صفحه قبلی
            }
        }
    }
}
