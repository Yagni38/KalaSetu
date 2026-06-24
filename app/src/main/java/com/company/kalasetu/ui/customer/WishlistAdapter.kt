package com.company.kalasetu.ui.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.Wishlist

class WishlistAdapter(
    private val list: ArrayList<Wishlist>,
    private val onRemove: (Wishlist) -> Unit
) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val txtName =
            view.findViewById<TextView>(
                R.id.txtWishlistName
            )

        val txtPrice =
            view.findViewById<TextView>(
                R.id.txtWishlistPrice
            )

        val btnRemove =
            view.findViewById<Button>(
                R.id.btnRemove
            )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_wishlist,
                    parent,
                    false
                )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = list[position]

        holder.txtName.text =
            item.productName

        holder.txtPrice.text =
            "₹${item.price}"

        holder.btnRemove.setOnClickListener {
            onRemove(item)
        }
    }

    override fun getItemCount() =
        list.size
}