package com.a_23_59.nikeproject.feature.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a_23_59.nikeproject.R
import com.a_23_59.nikeproject.common.formatPrice
import com.a_23_59.nikeproject.common.implementSpringAnimationTrait
import com.a_23_59.nikeproject.data.repo.product.Product
import com.a_23_59.nikeproject.services.imageloading.ImageLoadingService

const val VIEW_TYPE_ROUND = 0
const val VIEW_TYPE_GRID = 1
const val VIEW_TYPE_LIST = 2

class ProductAdapter(
    var myViewType: Int = VIEW_TYPE_ROUND,
    val imageLoadingService: ImageLoadingService
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var products = ArrayList<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var productItemClickListener: ProductClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutResId = when (viewType) {

            VIEW_TYPE_ROUND -> R.layout.product_item_layout_round_view

            VIEW_TYPE_GRID ->  R.layout.product_item_layout_grid_view

            VIEW_TYPE_LIST ->  R.layout.product_item_list_view

            else -> throw IllegalStateException("view type is invalid")
        }

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(layoutResId, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return myViewType
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindProducts(products[position])


    override fun getItemCount(): Int = products.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val productPrice: TextView = itemView.findViewById(R.id.tv_product_price)

        private val productTitle: TextView = itemView.findViewById(R.id.tv_product_title)

        private val previousProductPrice: TextView =
            itemView.findViewById(R.id.tv_product_previous_price)

        private val productImage: ImageView = itemView.findViewById(R.id.iv_product_image)


        fun bindProducts(product: Product) {

            productTitle.text = product.title

            imageLoadingService.load(itemView.context, product.image, productImage)

            productPrice.text = formatPrice(product.price)

            previousProductPrice.text = formatPrice(product.previousPrice)

            previousProductPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            itemView.implementSpringAnimationTrait()

            itemView.setOnClickListener {

                productItemClickListener?.onItemClicked(product)

            }


        }
    }


    interface ProductClickListener {

        fun onItemClicked(product: Product)
    }

}