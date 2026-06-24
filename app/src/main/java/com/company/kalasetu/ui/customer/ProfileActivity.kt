package com.company.kalasetu.ui.customer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R
import com.company.kalasetu.ui.auth.RoleSelectionActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        txtEmail.text =
            FirebaseAuth.getInstance().currentUser?.email

        btnLogout.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            startActivity(
                Intent(
                    this,
                    RoleSelectionActivity::class.java
                )
            )

            finish()
        }
    }
}