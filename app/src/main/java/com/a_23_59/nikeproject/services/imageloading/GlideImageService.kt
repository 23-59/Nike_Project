package com.a_23_59.nikeproject.services.imageloading

import android.content.Context
import android.view.RoundedCorner
import android.widget.ImageView
import com.a_23_59.nikeproject.data.repo.banner.Banner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class GlideImageService:ImageLoadingService {
    override fun load(context: Context, imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions.bitmapTransform( RoundedCorners(14)))
            .into(imageView)
    }


}