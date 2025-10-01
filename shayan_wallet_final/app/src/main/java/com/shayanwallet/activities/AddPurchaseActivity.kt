package com.shayanwallet.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.shayanwallet.R

class AddPurchaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_purchase)

        // نمونه: دکمه ذخیره خرید
        val saveBtn: Button = findViewById(R.id.savePurchaseBtn)
        saveBtn.setOnClickListener {
            // TODO: اضافه کردن منطق ذخیره خرید
        }
    }
}
