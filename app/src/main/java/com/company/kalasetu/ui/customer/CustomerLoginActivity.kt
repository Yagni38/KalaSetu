package com.company.kalasetu.ui.customer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R
import com.company.kalasetu.ui.customer.CustomerHomeActivity
import com.google.firebase.auth.FirebaseAuth

class CustomerLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_login)

        auth = FirebaseAuth.getInstance()

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(
                etEmail.text.toString(),
                etPassword.text.toString()
            ).addOnSuccessListener {
                startActivity(
                    Intent(this, CustomerHomeActivity::class.java)
                )
                finish()
            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}