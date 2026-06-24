package com.company.kalasetu.ui.customer

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.Product
import com.company.kalasetu.ui.artisan.OrdersActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CustomerHomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: ProductAdapter
    private lateinit var etSearch: EditText
    private lateinit var recyclerRecommended: RecyclerView
    private lateinit var recyclerRecent: RecyclerView
    private val productList=ArrayList<Product>()
    private val filteredList=ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customer_home)
        Toast.makeText(
            this,
            "Customer Home Opened",
            Toast.LENGTH_LONG
        ).show()
        val uid = FirebaseAuth.getInstance().currentUser?.uid

        Toast.makeText(
            this,
            "UID: $uid",
            Toast.LENGTH_LONG
        ).show()
        recyclerRecent = findViewById(R.id.recyclerRecent)
        recyclerRecent.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        recyclerRecommended =
            findViewById(R.id.recyclerRecommended)

        recyclerRecommended.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        val bottomNav =
            findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.nav_home -> {

                    true
                }

                R.id.nav_orders -> {

                    startActivity(
                        Intent(
                            this,
                            OrdersActivity::class.java
                        )
                    )

                    true
                }

                R.id.nav_wishlist -> {

                    startActivity(
                        Intent(
                            this,
                            WishlistActivity::class.java
                        )
                    )

                    true
                }

                R.id.nav_profile -> {

                    startActivity(
                        Intent(
                            this,
                            CustomerProfileActivity::class.java
                        )
                    )

                    true
                }

                else -> false
            }
        }
        findViewById<ImageButton>(
            R.id.btnNotification
        ).setOnClickListener {

            startActivity(

                Intent(
                    this,
                    NotificationActivity::class.java
                )
            )
        }

        etSearch = findViewById(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

                filterProducts(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        recyclerView = findViewById(R.id.recyclerProducts)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        adapter = ProductAdapter(productList)

        recyclerView.adapter = adapter

        loadProducts()
        loadRecentViews()
        loadRecommendations()
    }
    private fun loadProducts() {

        FirebaseFirestore.getInstance()
            .collection("Products")
            .get()
            .addOnSuccessListener {

                productList.clear()

                for (document in it.documents) {

                    val product =
                        document.toObject(Product::class.java)

                    if (product != null) {

                        productList.add(product)
                        filteredList.add(product)
                    }
                }

                adapter.notifyDataSetChanged()
            }
    }
    private fun loadRecentViews() {

        FirebaseFirestore.getInstance()
            .collection("Products")
            .limit(5)
            .get()
            .addOnSuccessListener {

                val recentList = ArrayList<Product>()

                for(doc in it.documents){

                    val product =
                        doc.toObject(Product::class.java)

                    if(product != null){

                        recentList.add(product)
                    }
                }

                recyclerRecent.adapter =
                    ProductAdapter(recentList)
            }
    }
    private fun loadRecommendations() {

        FirebaseFirestore.getInstance()
            .collection("Products")
            .limit(5)
            .get()
            .addOnSuccessListener {

                val recommendedList =
                    ArrayList<Product>()

                for(doc in it.documents){

                    val product =
                        doc.toObject(
                            Product::class.java
                        )

                    if(product != null){

                        recommendedList.add(product)
                    }
                }

                recyclerRecommended.adapter =
                    ProductAdapter(
                        recommendedList
                    )
            }
    }
    private fun filterProducts(query: String) {

        filteredList.clear()

        for (product in productList) {

            if (product.name.lowercase()
                    .contains(query.lowercase())
            ) {

                filteredList.add(product)
            }
        }

        adapter.updateList(filteredList)
    }

}