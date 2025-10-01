package com.shayanwallet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shayanwallet.R
import com.shayanwallet.models.Investment
import java.text.SimpleDateFormat
import java.util.*

class InvestmentAdapter(private val investments: List<Investment>) :
    RecyclerView.Adapter<InvestmentAdapter.InvestmentViewHolder>() {

    class InvestmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.investmentTitle)
        val amount: TextView = itemView.findViewById(R.id.investmentAmount)
        val date: TextView = itemView.findViewById(R.id.investmentDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvestmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_investment, parent, false)
        return InvestmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: InvestmentViewHolder, position: Int) {
        val investment = investments[position]
        holder.title.text = investment.title
        holder.amount.text = investment.amount.toString()
        val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        holder.date.text = sdf.format(investment.date)
    }

    override fun getItemCount(): Int = investments.size
}
