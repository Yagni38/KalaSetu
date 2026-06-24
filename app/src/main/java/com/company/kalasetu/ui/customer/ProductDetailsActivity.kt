package com.company.kalasetu.ui.customer

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.company.kalasetu.R
import android.widget.Button
import com.company.kalasetu.model.Review
import com.company.kalasetu.model.RecentView
import com.company.kalasetu.model.Report
import android.content.Intent

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var btnReport: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val img = findViewById<ImageView>(R.id.imgProduct)
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtPrice = findViewById<TextView>(R.id.txtPrice)
        val txtDescription = findViewById<TextView>(R.id.txtDescription)
        val btnBuyNow =
            findViewById<Button>(R.id.btnBuyNow)

        val btnWishlist =
            findViewById<Button>(R.id.btnWishlist)

        val btnChat =
            findViewById<Button>(R.id.btnChat)

        val btnShare =
            findViewById<Button>(R.id.btnShare)
        val ratingBar =
            findViewById<RatingBar>(R.id.ratingBar)
        btnReport = findViewById(R.id.btnReport)

        val etReview =
            findViewById<EditText>(R.id.etReview)

        val btnSubmit =
            findViewById<Button>(R.id.btnSubmitReview)

        val db = FirebaseFirestore.getInstance()
        btnBuyNow.setOnClickListener {

            Toast.makeText(
                this,
                "Order Placed",
                Toast.LENGTH_SHORT
            ).show()
        }
        btnReport.setOnClickListener {
           val productId = intent.getStringExtra("productId")?:""
            val report = Report(

                productId = productId,

                customerId =
                    FirebaseAuth.getInstance()
                        .currentUser?.uid ?: "",

                reason = "Fake Product"
            )

            FirebaseFirestore.getInstance()
                .collection("Reports")
                .add(report)
                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "Product Reported",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
        btnWishlist.setOnClickListener {

            val wishlist = hashMapOf(

                "productName" to txtName.text.toString(),

                "price" to txtPrice.text.toString(),

                "imageUrl" to intent.getStringExtra("image"),

                "userId" to FirebaseAuth.getInstance()
                    .currentUser?.uid
            )

            FirebaseFirestore.getInstance()
                .collection("wishlist")
                .add(wishlist)

            Toast.makeText(
                this,
                "Added To Wishlist",
                Toast.LENGTH_SHORT
            ).show()
        }
        btnShare.setOnClickListener {

            val shareIntent = Intent()

            shareIntent.action =
                Intent.ACTION_SEND

            shareIntent.type =
                "text/plain"

            shareIntent.putExtra(

                Intent.EXTRA_TEXT,

                "Check out this handmade product:\n\n" +
                        txtName.text.toString() +
                        "\nPrice: " +
                        txtPrice.text.toString()
            )

            startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Share Product"
                )
            )
        }
        btnChat.setOnClickListener {

            val intent = Intent(
                this,
                ChatActivity::class.java
            )

            intent.putExtra(
                "artisanId",
                "ARTISAN_ID"
            )

            startActivity(intent)
        }
        btnSubmit.setOnClickListener {

            val review = Review(
                FirebaseAuth.getInstance().currentUser?.email ?: "",
                ratingBar.rating,
                etReview.text.toString()
            )
            val productId=intent.getStringExtra("productId")?:""
            val recentView = RecentView(

                customerId =
                    FirebaseAuth.getInstance()
                        .currentUser?.uid ?: "",

                productId = productId
            )

            FirebaseFirestore.getInstance()
                .collection("RecentViews")
                .add(recentView)

            db.collection("Products")
                .document(productId)
                .collection("reviews")
                .add(review)
                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "Review Added",
                        Toast.LENGTH_SHORT
                    ).show()

                }

        }

        txtName.text = intent.getStringExtra("name")
        txtPrice.text = intent.getStringExtra("price")
        txtDescription.text = intent.getStringExtra("description")

        Glide.with(this)
            .load(intent.getStringExtra("image"))
            .into(img)
    }
}