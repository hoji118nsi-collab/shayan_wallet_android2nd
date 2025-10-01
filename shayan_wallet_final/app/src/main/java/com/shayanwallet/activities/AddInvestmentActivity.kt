package com.shayanwallet.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.shayanwallet.R
import com.shayanwallet.db.DatabaseClient
import com.shayanwallet.models.Investment
import java.util.*
import kotlin.concurrent.thread

class AddInvestmentActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var amountEditText: EditText
    private lateinit var saveInvestmentBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_investment)

        titleEditText = findViewById(R.id.titleEditText)
        amountEditText = findViewById(R.id.amountEditText)
        saveInvestmentBtn = findViewById(R.id.saveInvestmentBtn)

        saveInvestmentBtn.setOnClickListener {
            saveInvestment()
        }
    }

    private fun saveInvestment() {
        val title = titleEditText.text.toString()
        val amount = amountEditText.text.toString().toIntOrNull() ?: 0
        val investment = Investment(title = title, amount = amount, date = Date())

        thread {
            DatabaseClient.getDatabase(this).investmentDao().insert(investment)
            runOnUiThread {
                finish() // برگرد به صفحه قبلی
            }
        }
    }
}
