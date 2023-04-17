package com.example.activity6_grocerylistapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ProductListHolder( private val productList: ProductList,private val context: Context):
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

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                removeItem(position)
            }
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
    }

    override fun getItemCount() = productList.getItems().size

    fun removeItem(position: Int) {
        val removedItem = productList.removeItem(position)
        notifyItemRemoved(position)
        val message = "${removedItem.productTitle} removed from the list."
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
