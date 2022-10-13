package com.a_23_59.nikeproject.data.repo.banner

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Banner(

	@field:SerializedName("link_type")
	val linkType: Int,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("link_value")
	val linkValue: String
) : Parcelable
