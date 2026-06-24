package com.company.kalasetu.ui.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.Product
import com.google.firebase.firestore.FirebaseFirestore

class AdminProductAdapter(
    private val productList: ArrayList<Product>
) : RecyclerView.Adapter<AdminProductAdapter.ViewHolder>() {

    class ViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val txtName =
            view.findViewById<TextView>(
                R.id.txtName
            )

        val txtPrice =
            view.findViewById<TextView>(
                R.id.txtPrice
            )

        val btnDelete =
            view.findViewById<Button>(
                R.id.btnDelete
            )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_admin_product,
                    parent,
                    false
                )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val product =
            productList[position]

        holder.txtName.text =
            product.name

        holder.txtPrice.text =
            "₹${product.price}"

        holder.btnDelete.setOnClickListener {

            FirebaseFirestore.getInstance()
                .collection("Products")
                .document(product.id)
                .delete()
                .addOnSuccessListener {

                    Toast.makeText(
                        holder.itemView.context,
                        "Product Deleted",
                        Toast.LENGTH_SHORT
                    ).show()

                    productList.removeAt(position)

                    notifyItemRemoved(position)
                }
        }
    }
}