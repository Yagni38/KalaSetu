package com.company.kalasetu.ui.artisan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.Order
import com.google.firebase.firestore.FirebaseFirestore

class ManageOrderAdapter(
    private val list:ArrayList<Order>
) : RecyclerView.Adapter<
        ManageOrderAdapter.ViewHolder>() {

    class ViewHolder(view: View)
        : RecyclerView.ViewHolder(view){

        val txtProductName =
            view.findViewById<TextView>(
                R.id.txtProductName
            )

        val txtStatus =
            view.findViewById<TextView>(
                R.id.txtStatus
            )

        val btnAccept =
            view.findViewById<Button>(
                R.id.btnAccept
            )

        val btnShip =
            view.findViewById<Button>(
                R.id.btnShip
            )

        val btnDeliver =
            view.findViewById<Button>(
                R.id.btnDeliver
            )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_manage_order,
                    parent,
                    false
                )

        return ViewHolder(view)
    }

    override fun getItemCount() =
        list.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val order = list[position]

        holder.txtProductName.text =
            order.productName

        holder.txtStatus.text =
            order.status

        holder.btnAccept.setOnClickListener {

            FirebaseFirestore
                .getInstance()
                .collection("Orders")
                .document(order.orderId)
                .update(
                    "status",
                    "Accepted"
                )
        }

        holder.btnShip.setOnClickListener {

            FirebaseFirestore
                .getInstance()
                .collection("Orders")
                .document(order.orderId)
                .update(
                    "status",
                    "Shipped"
                )
        }

        holder.btnDeliver.setOnClickListener {

            FirebaseFirestore
                .getInstance()
                .collection("Orders")
                .document(order.orderId)
                .update(
                    "status",
                    "Delivered"
                )
        }
    }
}