package com.a_23_59.nikeproject.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.a_23_59.nikeproject.R
import com.a_23_59.nikeproject.common.EXTRA_KEY_DATA
import com.a_23_59.nikeproject.common.NikeFragment
import com.a_23_59.nikeproject.data.repo.product.Product
import com.a_23_59.nikeproject.data.repo.product.SORT_LATEST
import com.a_23_59.nikeproject.data.repo.product.SORT_POPULAR
import com.a_23_59.nikeproject.feature.all_products_list.ProductListActivity
import com.a_23_59.nikeproject.feature.product_detail.ProductDetailActivity
import com.google.android.material.snackbar.Snackbar
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainFragment : NikeFragment(), ProductAdapter.ProductClickListener {


    override val mContext: Context?
        get() = super.mContext

    override val rootView: CoordinatorLayout?
        get() = super.rootView


    private val TAG = "MainFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productAdapter: ProductAdapter by inject { parametersOf(VIEW_TYPE_ROUND) }

        val showAllLatestBtn: Button = view.findViewById(R.id.btn_show_all_latest)

        val showAllPopular: Button = view.findViewById(R.id.btn_show_all_popular)

        productAdapter.productItemClickListener = this

        val popularRecyclerViewAdapter: ProductAdapter by inject { parametersOf(VIEW_TYPE_ROUND) }

        popularRecyclerViewAdapter.productItemClickListener = this


        val viewModel: MainViewModel by viewModel()

        val latestRecyclerView: RecyclerView = view.findViewById(R.id.rv_latest_products)

        val popularRecyclerView: RecyclerView = view.findViewById(R.id.rv_most_popular_products)

        latestRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        latestRecyclerView.adapter = productAdapter

        popularRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        popularRecyclerView.adapter = popularRecyclerViewAdapter

        viewModel.latestProductLiveData.observe(viewLifecycleOwner) {
            Log.i(TAG, "data has been received ${it.size}")

            productAdapter.products = it as ArrayList<Product>
        }

        viewModel.popularProductLiveData.observe(viewLifecycleOwner) {

            popularRecyclerViewAdapter.products = it as ArrayList<Product>

        }

        viewModel.progressBarLiveData.observe(viewLifecycleOwner) {

            mSetProgressIndicator(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(view, "مشکل در اتصال به اینترنت", Snackbar.LENGTH_LONG).show()
        }

        viewModel.bannersLiveData.observe(viewLifecycleOwner) { banners ->

            val viewpager2: ViewPager2 = view.findViewById(R.id.banner_view_pager)

            val dotsIndicator = view.findViewById<WormDotsIndicator>(R.id.my_indicator)

            val sliderAdapter = SliderAdapter(this, banners)

            viewpager2.adapter = sliderAdapter

            dotsIndicator.attachTo(viewpager2)

        }

        showAllPopular.setOnClickListener {

            startActivity(Intent(requireContext(), ProductListActivity::class.java).apply {
                putExtra(EXTRA_KEY_DATA, SORT_POPULAR)

            })

        }

        showAllLatestBtn.setOnClickListener {

            startActivity(Intent(requireContext(), ProductListActivity::class.java).apply {
                putExtra(EXTRA_KEY_DATA, SORT_LATEST)
            })
        }


    }

    override fun onItemClicked(product: Product) {

        startActivity(Intent(requireContext(), ProductDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA, product)
        })
    }

}