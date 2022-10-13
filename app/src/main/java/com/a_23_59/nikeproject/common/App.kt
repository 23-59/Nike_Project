package com.a_23_59.nikeproject.common

import android.app.Application
import android.os.Bundle
import com.a_23_59.nikeproject.data.repo.banner.BannerImpl
import com.a_23_59.nikeproject.data.repo.banner.BannerRepository
import com.a_23_59.nikeproject.data.repo.banner.data_source.RemoteSourceBannerImpl
import com.a_23_59.nikeproject.data.repo.comment.CommentRepository
import com.a_23_59.nikeproject.data.repo.comment.CommentRepositoryImpl
import com.a_23_59.nikeproject.data.repo.comment.data_source.CommentDataSourceImpl
import com.a_23_59.nikeproject.data.repo.comment.detail.CommentDetailRepository
import com.a_23_59.nikeproject.data.repo.comment.detail.CommentDetailRepositoryImpl
import com.a_23_59.nikeproject.data.repo.comment.detail.data_source.CommentDetailDataSourceImpl
import com.a_23_59.nikeproject.data.repo.product.data_source.LocalSource
import com.a_23_59.nikeproject.data.repo.product.data_source.RemoteSource
import com.a_23_59.nikeproject.data.repo.product.repository.ProductRepository
import com.a_23_59.nikeproject.data.repo.product.repository.RepositoryImpl
import com.a_23_59.nikeproject.feature.all_products_list.ProductListViewModel
import com.a_23_59.nikeproject.feature.comments.CommentDetailViewModel
import com.a_23_59.nikeproject.feature.main.MainViewModel
import com.a_23_59.nikeproject.feature.main.ProductAdapter
import com.a_23_59.nikeproject.feature.product_detail.ProductDetailViewModel
import com.a_23_59.nikeproject.services.http.retrofitApiServiceInstance
import com.a_23_59.nikeproject.services.imageloading.GlideImageService
import com.a_23_59.nikeproject.services.imageloading.ImageLoadingService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class App : Application() {


    override fun onCreate() {
        super.onCreate()

        val myModules = module {

            single { retrofitApiServiceInstance() }

            single<ImageLoadingService> { GlideImageService() }

            factory<ProductRepository> { RepositoryImpl(RemoteSource(get()), LocalSource()) }

            factory<BannerRepository> { BannerImpl(RemoteSourceBannerImpl(get())) }

            factory { (viewType: Int) -> ProductAdapter(viewType, get()) }

            factory<CommentRepository> { CommentRepositoryImpl(CommentDataSourceImpl(get())) }

            factory<CommentDetailRepository> {
                CommentDetailRepositoryImpl(
                    CommentDetailDataSourceImpl(get())
                )
            }

            viewModel { MainViewModel(get(), get()) }

            viewModel { (bundle: Bundle) -> ProductDetailViewModel(bundle, get()) }

            viewModel { (productId: Int) -> CommentDetailViewModel(productId, get()) }

            viewModel { (sort: Int) -> ProductListViewModel(sort, get()) }

        }

        startKoin {

            androidContext(this@App)
            modules(myModules)
        }


    }
}