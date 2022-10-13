package com.a_23_59.nikeproject.data.repo.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("discount")
	val discount: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("previous_price")
	val previousPrice: Int,

	@field:SerializedName("status")
	val status: Int
):Parcelable

const val SORT_LATEST=0
const val SORT_POPULAR=1
const val SORT_MOST_EXPENSIVE=2
