package com.company.kalasetu.ui.artisan

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R
import com.google.firebase.firestore.FirebaseFirestore

class ArtisanDashboardActivity :
    AppCompatActivity() {
    private lateinit var txtProducts: TextView
    private lateinit var txtOrders: TextView
    private lateinit var txtArtisans: TextView

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_artisan_dashboard
        )

        txtProducts =
            findViewById<TextView>(
                R.id.txtProducts
            )

        txtOrders =
            findViewById<TextView>(
                R.id.txtOrders
            )
        txtArtisans = findViewById(R.id.txtArtisans)
        loadCounts()
    }
    private fun loadCounts(){
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
            .addOnSuccessListener{
                txtArtisans.text = "Artisans : ${it.size()}"
            }
    }
}