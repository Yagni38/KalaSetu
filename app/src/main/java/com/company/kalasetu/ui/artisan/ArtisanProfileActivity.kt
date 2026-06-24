package com.company.kalasetu.ui.artisan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R
import com.company.kalasetu.model.Artisan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.appcompat.app.AppCompatDelegate
import android.widget.Switch

class ArtisanProfileActivity :
    AppCompatActivity() {

    private lateinit var etName:EditText
    private lateinit var etEmail:EditText
    private lateinit var etPhone:EditText
    private lateinit var etCity:EditText
    private lateinit var etState:EditText

    private lateinit var btnSave:Button
    private lateinit var switchDarkMode: Switch
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_artisan_profile
        )

        etName =
            findViewById(R.id.etName)

        etEmail =
            findViewById(R.id.etEmail)

        etPhone =
            findViewById(R.id.etPhone)

        etCity =
            findViewById(R.id.etCity)

        etState =
            findViewById(R.id.etState)

        btnSave =
            findViewById(R.id.btnSave)

        btnSave.setOnClickListener {

            saveProfile()
        }
        switchDarkMode = findViewById(R.id.switchDarkMode)
        switchDarkMode.setOnCheckedChangeListener {

                _, isChecked ->

            if (isChecked) {

                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )

            } else {

                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }

    private fun saveProfile() {

        val uid =
            FirebaseAuth.getInstance()
                .currentUser?.uid ?: ""

        val artisan = Artisan(

            uid = uid,

            name =
                etName.text.toString(),

            email =
                etEmail.text.toString(),

            phone =
                etPhone.text.toString(),

            city =
                etCity.text.toString(),

            state =
                etState.text.toString()
        )

        FirebaseFirestore.getInstance()
            .collection("Artisans")
            .document(uid)
            .set(artisan)
            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "Profile Saved",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}