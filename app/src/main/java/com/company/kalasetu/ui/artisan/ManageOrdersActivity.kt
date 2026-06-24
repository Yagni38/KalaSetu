package com.company.kalasetu.ui.artisan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.Order
import com.google.firebase.firestore.FirebaseFirestore

class ManageOrdersActivity : AppCompatActivity() {

    private lateinit var recyclerOrders: RecyclerView

    private val orderList =
        ArrayList<Order>()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_manage_orders
        )

        recyclerOrders =
            findViewById(
                R.id.recyclerOrders
            )

        recyclerOrders.layoutManager =
            LinearLayoutManager(this)

        loadOrders()
    }

    private fun loadOrders() {

        FirebaseFirestore
            .getInstance()
            .collection("Orders")
            .get()
            .addOnSuccessListener {

                orderList.clear()

                for(doc in it.documents){

                    val order =
                        doc.toObject(
                            Order::class.java
                        )

                    if(order != null){

                        order.orderId =
                            doc.id

                        orderList.add(order)
                    }
                }

                recyclerOrders.adapter =
                    ManageOrderAdapter(
                        orderList
                    )
            }
    }
}