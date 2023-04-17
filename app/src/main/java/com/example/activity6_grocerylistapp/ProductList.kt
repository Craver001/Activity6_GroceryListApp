package com.example.activity6_grocerylistapp

class ProductList {

    private val productList = mutableListOf<ProductClass>()

    fun getItems(): List<ProductClass> {
        return productList
    }
    fun addItem(product: ProductClass) {
        productList.add(product)
    }

    fun removeItem(position: Int): ProductClass {
        return productList.removeAt(position)
    }





}
