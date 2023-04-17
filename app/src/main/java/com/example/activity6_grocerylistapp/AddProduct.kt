package com.example.activity6_grocerylistapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.activity6_grocerylistapp.databinding.ActivityAddProductBinding

class AddProduct : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var productList: ProductList
    private lateinit var imageUri: Uri

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            binding.productImage.setImageURI(imageUri)
            binding.productImage.setBackgroundResource(0) // remove background
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productList = ProductList()

        binding.productImage.setOnClickListener {
            openGallery()
        }

        binding.addProduct.setOnClickListener {
            val productName = binding.productName.text.toString()
            val productDescription = binding.description.text.toString()
            val productPrice = binding.price.text.toString()

            val newProduct = ProductClass(imageUri, productName, productPrice, productDescription)

            val resultIntent = Intent().apply {
                putExtra(MainActivity.EXTRA_PRODUCT, newProduct as? android.os.Parcelable)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun openGallery() {
        getContent.launch("image/*")
    }
}
