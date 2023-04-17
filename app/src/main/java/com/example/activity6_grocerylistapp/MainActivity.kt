package com.example.activity6_grocerylistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addProduct: Button
    private lateinit var productList: ProductList
    private lateinit var adapter: ProductListHolder

    companion object {
        const val ADD_PRODUCT_REQUEST_CODE = 1
        const val EXTRA_PRODUCT = "extra_product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addProduct = findViewById(R.id.addItem)
        recyclerView = findViewById(R.id.recyclerView)

        productList = ProductList()
        adapter = ProductListHolder(productList,this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val swipeToDeleteCallback = SwipeToDeleteCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        addProduct.setOnClickListener {
            val intent = Intent(this@MainActivity, AddProduct::class.java)
            startActivityForResult(intent, ADD_PRODUCT_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PRODUCT_REQUEST_CODE && resultCode == RESULT_OK) {
            val newProduct = data?.getParcelableExtra<ProductClass>(EXTRA_PRODUCT) as? ProductClass
            newProduct?.let {
                productList.addItem(it)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
