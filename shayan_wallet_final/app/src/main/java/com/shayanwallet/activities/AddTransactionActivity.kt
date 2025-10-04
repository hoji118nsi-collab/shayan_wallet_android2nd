package com.shayanwallet.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.shayanwallet.R
import com.shayanwallet.db.DatabaseClient
import com.shayanwallet.models.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var amountEditText: EditText
    private lateinit var saveTransactionBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        titleEditText = findViewById(R.id.titleEditText)
        amountEditText = findViewById(R.id.amountEditText)
        saveTransactionBtn = findViewById(R.id.saveTransactionBtn)

        saveTransactionBtn.setOnClickListener {
            saveTransaction()
        }
    }

    private fun saveTransaction() {
        val title = titleEditText.text.toString()
        val amount = amountEditText.text.toString().toIntOrNull() ?: 0
        val transaction = Transaction(title = title, amount = amount)

        CoroutineScope(Dispatchers.IO).launch {
            DatabaseClient.getDatabase(this@AddTransactionActivity)
                .transactionDao()
                .insert(transaction)

            runOnUiThread {
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}
