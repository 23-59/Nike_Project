package com.a_23_59.nikeproject.data.repo.product.data_source

import com.a_23_59.nikeproject.data.repo.product.Product
import com.a_23_59.nikeproject.data.repo.product.SORT_LATEST
import com.a_23_59.nikeproject.services.http.ApiService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RemoteSource(private val apiService: ApiService) : ProductDataSource {

    override fun getProducts(sort:Int): Single<List<Product>> {

        return apiService.sendRequest(sort.toString())
    }


    override fun getFavorites(): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun deleteFromFavorites(): Completable {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(): Completable {
        TODO("Not yet implemented")
    }
}