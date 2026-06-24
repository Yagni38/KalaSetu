package com.company.kalasetu.ui.admin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Intent
import android.widget.Button

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var txtProducts: TextView
    private lateinit var txtOrders: TextView
    private lateinit var txtArtisans: TextView
    private lateinit var btnReports: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_admin_dashboard
        )
        btnReports = findViewById(R.id.btnReports)
        btnReports.setOnClickListener {

            startActivity(

                Intent(
                    this,
                    AdminReportsActivity::class.java
                )
            )
        }
        txtProducts =
            findViewById(R.id.txtProducts)

        txtOrders =
            findViewById(R.id.txtOrders)

        txtArtisans =
            findViewById(R.id.txtArtisans)

        loadCounts()
    }

    private fun loadCounts() {

        FirebaseFirestore.getInstance()
            .collection("Products")
            .get()
            .addOnSuccessListener {

                txtProducts.text =
                    "Products : ${it.size()}"
            }

        FirebaseFirestore.getInstance()
            .collection("Orders")
            .get()
            .addOnSuccessListener {

                txtOrders.text =
                    "Orders : ${it.size()}"
            }

        FirebaseFirestore.getInstance()
            .collection("Artisans")
            .get()
            .addOnSuccessListener {

                txtArtisans.text =
                    "Artisans : ${it.size()}"
            }
    }
}