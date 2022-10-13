package com.a_23_59.nikeproject.feature.product_detail

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a_23_59.nikeproject.R
import com.a_23_59.nikeproject.common.EXTRA_KEY_ID
import com.a_23_59.nikeproject.common.NikeActivity
import com.a_23_59.nikeproject.common.formatPrice
import com.a_23_59.nikeproject.common.view.CustomToolbar
import com.a_23_59.nikeproject.data.repo.comment.Comment
import com.a_23_59.nikeproject.data.repo.comment.CommentAdapter
import com.a_23_59.nikeproject.feature.comments.CommentDetailActivity
import com.a_23_59.nikeproject.services.imageloading.ImageLoadingService
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailActivity : NikeActivity() {

    private val productDetailViewModel: ProductDetailViewModel by viewModel { parametersOf(intent.extras) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        mSetProgressIndicator(true)

        val imageLoadService: ImageLoadingService by inject()

        val ivProductImage: ImageView = findViewById(R.id.iv_product_detail)

        val tvProductTitle: TextView = findViewById(R.id.tv_title_product)

        val tvPrice: TextView = findViewById(R.id.tv_product_detail_price)

        val tvPreviousPrice: TextView = findViewById(R.id.tv_product_detail_previous_price)

        val nestedScrollView: NestedScrollView = findViewById(R.id.productNestedScrollView)

        val toolbar: CardView = findViewById(R.id.toolbar_title)

        val toolbarTitle: TextView = findViewById(R.id.title)

        val icAddingToFavorites: ImageView = findViewById(R.id.ic_adding_to_favorites)

        val icBack: ImageView = findViewById(R.id.ic_back)

        val commentsRecyclerView: RecyclerView = findViewById(R.id.rv_comments)

        val showAllComments: Button = findViewById(R.id.btn_show_all_comments)

        val productCommentAdapter = CommentAdapter()

        productDetailViewModel.singleProductLiveData.observe(this) { product ->


            imageLoadService.load(this, product.image, ivProductImage)

            tvProductTitle.text = product.title

            toolbarTitle.text = product.title

            tvPrice.text = formatPrice(product.price)

            tvPreviousPrice.text = formatPrice(product.previousPrice)

            tvPreviousPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            showAllComments.setOnClickListener {

                startActivity(Intent(this, CommentDetailActivity::class.java).apply {
                    putExtra(EXTRA_KEY_ID, product.id)
                })

            }


        }

        productDetailViewModel.commentsListLiveData.observe(this) {

            if (it.size > 3)
                showAllComments.visibility = View.VISIBLE

            productCommentAdapter.comments = it as ArrayList<Comment>

            commentsRecyclerView.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)

            commentsRecyclerView.adapter = productCommentAdapter

        }

        productDetailViewModel.progressBarLiveData.observe(this) {

            mSetProgressIndicator(it)
        }



        ivProductImage.post {


            nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                if (scrollY <= 50) {

                    icBack.setImageResource(R.drawable.ic_round_arrow_back_black_24)
                    icAddingToFavorites.setImageResource(R.drawable.ic_round_favorite_border_black_24)
                } else {
                    icBack.setImageResource(R.drawable.ic_round_arrow_back_24)
                    icAddingToFavorites.setImageResource(R.drawable.ic_round_favorite_border_24)
                }


                toolbar.alpha = scrollY.toFloat() / ivProductImage.height.toFloat()

                ivProductImage.translationY = scrollY.toFloat() / 2

            })

        }

        findViewById<ImageView>(R.id.ic_back).setOnClickListener{
            finish()
        }


    }


}