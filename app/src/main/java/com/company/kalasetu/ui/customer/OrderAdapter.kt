package com.company.kalasetu.ui.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.Order

class OrderAdapter(
    private val list: ArrayList<Order>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val txtProductName =
            view.findViewById<TextView>(
                R.id.txtProductName
            )

        val txtPrice =
            view.findViewById<TextView>(
                R.id.txtPrice
            )

        val txtStatus =
            view.findViewById<TextView>(
                R.id.txtStatus
            )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_order,
                    parent,
                    false
                )

        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(
        holder: OrderViewHolder,
        position: Int
    ) {

        val order = list[position]

        holder.txtProductName.text =
            order.productName

        holder.txtPrice.text =
            "order.price"

        holder.txtStatus.text =
            "Status : ${order.status}"
    }
}