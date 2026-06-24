package com.company.kalasetu.ui.customer
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CustomerProfileActivity : AppCompatActivity() {
    private lateinit var txtName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtPhone: TextView
    private lateinit var txtCity: TextView
    private lateinit var txtState: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customer_profile)

        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPhone = findViewById(R.id.txtPhone)
        txtCity = findViewById(R.id.txtCity)
        txtState = findViewById(R.id.txtState)

        loadUserData()
    }

    private fun loadUserData() {

        val uid =
            FirebaseAuth.getInstance()
                .currentUser?.uid

        FirebaseFirestore.getInstance()
            .collection("users")
            .document(uid!!)
            .get()
            .addOnSuccessListener { document ->

                txtName.text =
                    "👤 Name: ${document.getString("Name")}"

                txtEmail.text =
                    "📧 Email: ${document.getString("Email")}"

                txtPhone.text =
                    "📱 Phone: ${document.getString("phone")}"

                txtCity.text =
                    "🏙 City: ${document.getString("city")}"

                txtState.text =
                    "📍 State: ${document.getString("state")}"
            }
    }
}