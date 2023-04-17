package com.example.activity6_grocerylistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductListHolder(private val productList: ProductList):
    RecyclerView.Adapter<ProductListHolder.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage = itemView.findViewById<ImageView>(R.id.productImage)
        private val productName = itemView.findViewById<TextView>(R.id.productName)
        private val price = itemView.findViewById<TextView>(R.id.price)
        private val description = itemView.findViewById<TextView>(R.id.description)

        fun bind(product: ProductClass) {
            product.productImage?.let { uri ->
                Glide.with(itemView.context)
                    .load(uri)
                    .into(productImage)
            }
            productName.text = product.productTitle
            price.text = product.price
            description.text = product.descriptions
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_design, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList.getItems()[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            removeItem(position)
        }
    }

    override fun getItemCount() = productList.getItems().size

    fun removeItem(position: Int) {
        productList.removeItem(position)
        notifyItemRemoved(position)
    }
}
