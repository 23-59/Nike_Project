package com.a_23_59.nikeproject.services.imageloading

import android.content.Context
import android.widget.ImageView
import com.a_23_59.nikeproject.data.repo.banner.Banner

interface ImageLoadingService {

    fun load(context:Context, imageUrl: String, imageView: ImageView)
}