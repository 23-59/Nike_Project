package com.a_23_59.nikeproject.feature.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.a_23_59.nikeproject.common.CustomViewModel
import com.a_23_59.nikeproject.data.repo.banner.Banner
import com.a_23_59.nikeproject.data.repo.banner.BannerRepository
import com.a_23_59.nikeproject.data.repo.product.Product
import com.a_23_59.nikeproject.data.repo.product.SORT_LATEST
import com.a_23_59.nikeproject.data.repo.product.SORT_POPULAR
import com.a_23_59.nikeproject.data.repo.product.repository.ProductRepository
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(productRepository: ProductRepository,bannerRepository: BannerRepository) : CustomViewModel() {

    private val TAG = "MainViewModel"

    val latestProductLiveData = MutableLiveData<List<Product>>()
    val popularProductLiveData =MutableLiveData<List<Product>>()
    val bannersLiveData=MutableLiveData<List<Banner>>()

    init {

        progressBarLiveData.value=true

        productRepository.getProducts(SORT_LATEST).subscribeOn(Schedulers.io())
            .doFinally { progressBarLiveData.postValue(false) }
            .subscribe(object : SingleObserver<List<Product>> {

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(products: List<Product>) {
                    Log.i(TAG, "product has been received ")
                   latestProductLiveData.postValue(products)
                }

                override fun onError(e: Throwable) {

                    Log.e(TAG, "onError: ",e )

                    errorLiveData.postValue(e)
                }
            })

        productRepository.getProducts(SORT_POPULAR)
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<List<Product>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Product>) {

                    popularProductLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {

                    errorLiveData.postValue(e)
                }
            })

        bannerRepository.getBanners()
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<List<Banner>> {
                override fun onSubscribe(d: Disposable) {
                   compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Banner>) {
                    Log.i(TAG, "onSuccess: banner picture has been received")
                    bannersLiveData.postValue(t)

                }

                override fun onError(e: Throwable) {
                    Log.i(TAG, "onError: an error has occurred")
                }

            })
    }

}