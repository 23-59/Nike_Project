package com.a_23_59.nikeproject.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.a_23_59.nikeproject.R

class CustomToolbar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var onBackButtonClickListener: View.OnClickListener? = null
    set(value) {
        field=value
        findViewById<ImageView>(R.id.iv_back).setOnClickListener(onBackButtonClickListener)
    }

    init {
        inflate(context, R.layout.custom_toolbar_layout, this)

        if (attrs != null) {

            val attribute = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar)

            val title = attribute.getString(R.styleable.CustomToolbar_customTitle)

            if (title != null && title.isNotEmpty()) {
                findViewById<TextView>(R.id.tv_title).text = title
            }



        }
    }
}