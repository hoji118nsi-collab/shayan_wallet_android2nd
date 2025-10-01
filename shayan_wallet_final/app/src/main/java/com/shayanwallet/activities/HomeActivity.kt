package com.shayanwallet.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import com.shayanwallet.R
import com.shayanwallet.adapters.TransactionAdapter
import com.shayanwallet.adapters.InvestmentAdapter
import com.shayanwallet.db.DatabaseClient
import com.shayanwallet.models.Transaction
import com.shayanwallet.models.Investment
import kotlin.concurrent.thread

class HomeActivity : AppCompatActivity() {

    private lateinit var transactionsRecyclerView: RecyclerView
    private lateinit var addTransactionBtn: Button
    private var transactionList: MutableList<Transaction> = mutableListOf()
    private lateinit var transactionAdapter: TransactionAdapter

    private lateinit var investmentsRecyclerView: RecyclerView
    private lateinit var addInvestmentBtn: Button
    private var investmentList: MutableList<Investment> = mutableListOf()
    private lateinit var investmentAdapter: InvestmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // ---- تراکنش‌ها ----
        transactionsRecyclerView = findViewById(R.id.transactionsRecyclerView)
        addTransactionBtn = findViewById(R.id.addTransactionBtn)
        transactionAdapter = TransactionAdapter(transactionList)
        transactionsRecyclerView.layoutManager = LinearLayoutManager(this)
        transactionsRecyclerView.adapter = transactionAdapter
        loadTransactions()
        addTransactionBtn.setOnClickListener {
            startActivity(Intent(this, AddTransactionActivity::class.java))
        }

        // ---- سرمایه‌گذاری‌ها ----
        investmentsRecyclerView = findViewById(R.id.investmentsRecyclerView)
        addInvestmentBtn = findViewById(R.id.addInvestmentBtn)
        investmentAdapter = InvestmentAdapter(investmentList)
        investmentsRecyclerView.layoutManager = LinearLayoutManager(this)
        investmentsRecyclerView.adapter = investmentAdapter
        loadInvestments()
        addInvestmentBtn.setOnClickListener {
            startActivity(Intent(this, AddInvestmentActivity::class.java))
        }
    }

    private fun loadTransactions() {
        thread {
            val transactions = DatabaseClient.getDatabase(this).transactionDao().getAll()
            runOnUiThread {
                transactionList.clear()
                transactionList.addAll(transactions)
                transactionAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun loadInvestments() {
        thread {
            val investments = DatabaseClient.getDatabase(this).investmentDao().getAll()
            runOnUiThread {
                investmentList.clear()
                investmentList.addAll(investments)
                investmentAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadTransactions()
        loadInvestments()
    }
}
