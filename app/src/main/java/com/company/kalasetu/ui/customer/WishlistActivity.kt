package com.company.kalasetu.ui.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.Wishlist
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class WishlistActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private val wishlistList =
        ArrayList<Wishlist>()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_wishlist
        )

        recyclerView =
            findViewById(
                R.id.recyclerWishlist
            )

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        loadWishlist()
    }

    private fun loadWishlist() {

        val uid =
            FirebaseAuth.getInstance()
                .currentUser?.uid
                ?: return

        FirebaseFirestore.getInstance()
            .collection("wishlist")
            .whereEqualTo(
                "userId",
                uid
            )
            .get()
            .addOnSuccessListener {

                wishlistList.clear()

                for (doc in it.documents) {

                    val item =
                        doc.toObject(
                            Wishlist::class.java
                        )

                    if (item != null) {

                        item.documentId =
                            doc.id

                        wishlistList.add(item)
                    }
                }

                recyclerView.adapter =
                    WishlistAdapter(
                        wishlistList
                    ) { item ->

                        removeWishlist(
                            item
                        )
                    }
            }
    }

    private fun removeWishlist(
        item: Wishlist
    ) {

        FirebaseFirestore.getInstance()
            .collection("wishlist")
            .document(
                item.documentId
            )
            .delete()

        loadWishlist()
    }
}