package com.company.kalasetu.ui.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.company.kalasetu.R
import com.company.kalasetu.model.Product
import android.widget.Toast
import android.widget.Button
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.company.kalasetu.model.Order
import com.company.kalasetu.ui.customer.ProductDetailsActivity
import com.google.firebase.firestore.FirebaseFirestore

class ProductAdapter(
    private var list: ArrayList<Product>
) :

    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val img =
            view.findViewById<ImageView>(R.id.imgItem)

        val name =
            view.findViewById<TextView>(R.id.txtName)

        val price =
            view.findViewById<TextView>(R.id.txtPrice)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_product,
                parent,
                false
            )

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {

        return list.size
    }
    fun updateList(newList: ArrayList<Product>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {

        val product = list[position]

        holder.name.text = product.name
        holder.price.text = product.price
        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.img)

        holder.itemView.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                ProductDetailsActivity::class.java
            )

            intent.putExtra("name", product.name)
            intent.putExtra("price", product.price)
            intent.putExtra("description", product.description)
            intent.putExtra("image", product.imageUrl)
            intent.putExtra("productId",product.id)

            holder.itemView.context.startActivity(intent)
        }
    }
}