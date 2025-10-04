package com.shayanwallet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shayanwallet.R
import com.shayanwallet.models.Purchase
import java.text.SimpleDateFormat
import java.util.*

class PurchaseAdapter(private val purchases: List<Purchase>) :
    RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    inner class PurchaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
        val amountTextView: TextView = itemView.findViewById(R.id.costTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_purchase, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = purchases[position]
        holder.titleTextView.text = purchase.title
        holder.amountTextView.text = purchase.amount.toString()

        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        holder.dateTextView.text = formatter.format(purchase.date)
    }

    override fun getItemCount(): Int = purchases.size
}
