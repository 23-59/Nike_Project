package com.a_23_59.nikeproject.feature.all_products_list

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a_23_59.nikeproject.R
import com.a_23_59.nikeproject.common.EXTRA_KEY_DATA
import com.a_23_59.nikeproject.common.NikeActivity
import com.a_23_59.nikeproject.data.repo.product.Product
import com.a_23_59.nikeproject.feature.main.ProductAdapter
import com.a_23_59.nikeproject.feature.main.VIEW_TYPE_GRID
import com.a_23_59.nikeproject.feature.main.VIEW_TYPE_LIST
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ProductListActivity : NikeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val productListViewModel: ProductListViewModel by viewModel {
            parametersOf(
                intent.extras!!.getInt(
                    EXTRA_KEY_DATA
                )
            )
        }

        val productAdapter: ProductAdapter by inject { parametersOf(VIEW_TYPE_GRID) }


        val recyclerViewInstance: RecyclerView = findViewById(R.id.rv_product_list)

        val viewTypeBtn: ImageView = findViewById(R.id.iv_view_type)

        val tvSort: TextView = findViewById(R.id.tv_sort)

        val viewSort: View = findViewById(R.id.view_sort)

        val gridLayoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        recyclerViewInstance.layoutManager = gridLayoutManager

        viewTypeBtn.setOnClickListener {

            if (productAdapter.myViewType == VIEW_TYPE_GRID) {

                productAdapter.myViewType = VIEW_TYPE_LIST

                viewTypeBtn.setImageResource(R.drawable.ic_round_view_list_24)

                gridLayoutManager.spanCount = 1

                productAdapter.notifyDataSetChanged()

            } else if (productAdapter.myViewType == VIEW_TYPE_LIST) {

                productAdapter.myViewType = VIEW_TYPE_GRID

                viewTypeBtn.setImageResource(R.drawable.ic_round_grid_on_24)

                gridLayoutManager.spanCount = 2

                productAdapter.notifyDataSetChanged()
            }

        }

        viewSort.setOnClickListener {



        }

        productListViewModel.sortTitleLiveData.observe(this) {

            tvSort.text = getString(it)

        }

        productListViewModel.progressBarLiveData.observe(this) {

            mSetProgressIndicator(it)

        }

        productListViewModel.productLiveData.observe(this) {

            productAdapter.products = it as ArrayList<Product>
            recyclerViewInstance.adapter = productAdapter

        }
    }
}