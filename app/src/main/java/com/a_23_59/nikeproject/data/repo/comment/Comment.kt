package com.a_23_59.nikeproject.data.repo.comment

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("author")
	val author: Author,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("content")
	val content: String
) : Parcelable

@Parcelize
data class Author(

	@field:SerializedName("email")
	val email: String
) : Parcelable
