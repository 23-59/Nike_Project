package com.a_23_59.nikeproject.feature.all_products_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.a_23_59.nikeproject.R
import com.a_23_59.nikeproject.common.CustomViewModel
import com.a_23_59.nikeproject.data.repo.product.Product
import com.a_23_59.nikeproject.data.repo.product.repository.ProductRepository
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductListViewModel(
    private var sort: Int,
    private val productRepository: ProductRepository
) :
    CustomViewModel() {

    val productLiveData = MutableLiveData<List<Product>>()

    val sortTitleLiveData = MutableLiveData<Int>()

    private val sortArray = arrayOf(R.string.latest, R.string.most_popular, R.string.most_expensive)

    init {
        getProducts()
        sortTitleLiveData.value = sortArray[sort]
    }

    private fun getProducts() {

        progressBarLiveData.value = true
        productRepository.getProducts(sort)
            .subscribeOn(Schedulers.io())
            .doFinally { progressBarLiveData.postValue(false) }
            .subscribe(object : SingleObserver<List<Product>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Product>) {
                    productLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.e("error", "onError: ", e)
                }
            })

    }

    fun onSortChangedByUser(sort: Int) {

        this.sort = sort

        sortTitleLiveData.value = sort

        getProducts()


    }
}