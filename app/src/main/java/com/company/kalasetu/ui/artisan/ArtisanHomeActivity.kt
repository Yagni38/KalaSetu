package com.company.kalasetu.ui.artisan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R

class ArtisanHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artisan_home)

        val btnAddProduct =
            findViewById<Button>(R.id.btnAddProduct)

        val btnOrders =
            findViewById<Button>(R.id.btnOrders)

        val btnSalesAnalytics =
            findViewById<Button>(R.id.btnSalesAnalytics)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        btnAddProduct.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AddProductActivity::class.java
                )
            )
        }

        btnOrders.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ManageOrdersActivity::class.java
                )
            )
        }

        btnSalesAnalytics.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    SalesAnalyticsActivity::class.java
                )
            )
        }
        btnProfile.setOnClickListener{
            startActivity(
                Intent(
                    this,ArtisanProfileActivity::class.java
                )
            )
        }
    }
}