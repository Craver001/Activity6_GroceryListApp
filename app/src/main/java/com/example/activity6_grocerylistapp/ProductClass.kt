package com.example.activity6_grocerylistapp

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

class ProductClass(
    val productImage: Uri?,
    val productTitle: String,
    val price: String,
    val descriptions: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Uri::class.java.classLoader),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(productImage, flags)
        parcel.writeString(productTitle)
        parcel.writeString(price)
        parcel.writeString(descriptions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductClass> {
        override fun createFromParcel(parcel: Parcel): ProductClass {
            return ProductClass(parcel)
        }

        override fun newArray(size: Int): Array<ProductClass?> {
            return arrayOfNulls(size)
        }
    }
}
