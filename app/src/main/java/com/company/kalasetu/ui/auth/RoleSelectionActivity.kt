package com.company.kalasetu.ui.auth
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R
import com.company.kalasetu.ui.customer.CustomerLoginActivity
import com.company.kalasetu.ui.admin.AdminDashboardActivity

class RoleSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)

        // Initialize buttons
        val btnArtisan = findViewById<Button>(R.id.btnArtisan)
        val btnCustomer = findViewById<Button>(R.id.btnCustomer)
        val btnAdmin = findViewById<Button>(R.id.btnAdmin)
        // Artisan login
        btnArtisan.setOnClickListener {
            startActivity(
                Intent(this, ArtisanLoginActivity::class.java)
            )
        }

        // Customer login
        btnCustomer.setOnClickListener {
            startActivity(
                Intent(this, CustomerLoginActivity::class.java)
            )
        }
        btnAdmin.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AdminDashboardActivity::class.java
                )
            )
        }
    }
}