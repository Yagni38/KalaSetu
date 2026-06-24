package com.company.kalasetu.ui.artisan

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R
import com.google.firebase.firestore.FirebaseFirestore
import com.company.kalasetu.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class AddProductActivity : AppCompatActivity() {

    private lateinit var etProductName: EditText
    private lateinit var etPrice: EditText
    private lateinit var etDescription: EditText
    private lateinit var spinnerCategory:Spinner
    private lateinit var imgProduct: ImageView

    private lateinit var btnPickImage: Button
    private lateinit var btnRecord: Button
    private lateinit var etStock:EditText

    private lateinit var btnGenerateDescription:Button
    private lateinit var btnShare: Button
    private lateinit var btnUpload: Button
    private var artisanId = ""

    private var imageUri: Uri? = null

    // Image Picker
    private val imagePicker =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->

            if (uri != null) {

                imageUri = uri

                imgProduct.setImageURI(uri)
            }
        }

    // Voice Input
    private val speechLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == Activity.RESULT_OK) {

                val data = result.data

                val resultText =
                    data?.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS
                    )

                etDescription.setText(
                    resultText?.get(0)
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_product)

        requestPermissions(
            arrayOf(
                Manifest.permission.RECORD_AUDIO
            ),
            1
        )
        etStock = findViewById(R.id.etStock)
        etProductName =
            findViewById(R.id.etProductName)

        etPrice =
            findViewById(R.id.etPrice)

        etDescription =
            findViewById(R.id.etDescription)
        spinnerCategory=findViewById(R.id.spinnerCategory)
        // CATEGORY CODE STARTS HERE
        val categories = arrayOf(
            "Paintings",
            "Handmade Crafts",
            "Jewellery",
            "Clothes",
            "Home Decor",
            "Toys",
            "Pottery"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )

        spinnerCategory.adapter = adapter

        imgProduct =
            findViewById(R.id.imgProduct)

        btnPickImage =
            findViewById(R.id.btnPickImage)

        btnRecord =
            findViewById(R.id.btnRecord)

        btnUpload =
            findViewById(R.id.btnUpload)
        btnGenerateDescription= findViewById(R.id.btnGenerateDescription)
        btnShare = findViewById(R.id.btnShare)

        // Pick Image
        btnPickImage.setOnClickListener {

            imagePicker.launch("image/*")
        }

        // Voice Description
        btnRecord.setOnClickListener {

            startVoiceInput()
        }

        // Upload Product
        btnUpload.setOnClickListener {

            uploadProduct()
        }
        btnGenerateDescription.setOnClickListener {

            val name =
                etProductName.text.toString()

            if(name.isEmpty()){

                Toast.makeText(
                    this,
                    "Enter Product Name",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val description =

                "This handmade $name is crafted by skilled artisans using traditional techniques. It is unique, durable and suitable for gifting, decoration and everyday use."

            etDescription.setText(description)
        }

        btnShare.setOnClickListener {

            val productName =
                intent.getStringExtra("name")

            val price =
                intent.getStringExtra("price")

            val shareIntent = Intent()

            shareIntent.action =
                Intent.ACTION_SEND

            shareIntent.type = "text/plain"

            shareIntent.putExtra(
                Intent.EXTRA_TEXT,

                "Check out this product on KalaSetu\n\n" +
                        "Product : $productName\n" +
                        "Price : ₹$price"
            )

            startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Share Product"
                )
            )
        }

    }

    private fun startVoiceInput() {

        val intent =
            Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH
            )

        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )

        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Speak product description"
        )

        try {

            speechLauncher.launch(intent)

        } catch (e: Exception) {

            Toast.makeText(
                this,
                e.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun uploadProduct() {

        if (imageUri == null) {

            Toast.makeText(
                this,              "Select Image",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val config = mapOf(
            "cloud_name" to "dukx97xqt",
            "api_key" to "379628158134154",
            "api_secret" to "ar8fONXV16CwA3lyMbYXGei3HD8"
        )
       try {
           com.cloudinary.android.MediaManager.init(this, config)
       } catch(e: Exception){

       }

        com.cloudinary.android.MediaManager.get()
            .upload(imageUri)
            .callback(object :
                com.cloudinary.android.callback.UploadCallback {

                override fun onStart(requestId: String?) {
                }

                override fun onProgress(
                    requestId: String?,
                    bytes: Long,
                    totalBytes: Long
                ) {
                }

                override fun onSuccess(
                    requestId: String?,
                    resultData: MutableMap<Any?, Any?>?
                ) {

                    val imageUrl =
                        resultData?.get("secure_url").toString()

                    val product = Product(

                        id = "",

                        name = etProductName.text.toString(),

                        price = etPrice.text.toString(),

                        description =
                            etDescription.text.toString(),

                        imageUrl = imageUrl,
                        category = spinnerCategory.selectedItem.toString(),

                        stock =
                            etStock.text.toString()
                                .toIntOrNull() ?: 0
                    )

                    FirebaseFirestore.getInstance()
                        .collection("Products")
                        .add(product)
                        .addOnSuccessListener {

                            Toast.makeText(
                                this@AddProductActivity,
                                "Product Uploaded",
                                Toast.LENGTH_LONG
                            ).show()

                            finish()
                        }
                }

                override fun onError(
                    requestId: String?,
                    error: com.cloudinary.android.callback.ErrorInfo?
                ) {

                    Toast.makeText(
                        this@AddProductActivity,
                        error?.description,
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onReschedule(
                    requestId: String?,
                    error: com.cloudinary.android.callback.ErrorInfo?
                ) {
                }
            })
            .dispatch()
    }

}