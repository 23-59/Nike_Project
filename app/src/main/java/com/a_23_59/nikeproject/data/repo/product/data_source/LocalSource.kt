package com.a_23_59.nikeproject.data.repo.product.data_source

import com.a_23_59.nikeproject.data.repo.product.Product
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class LocalSource:ProductDataSource {
    override fun getProducts(sort:Int): Single<List<Product>> {
        TODO("Not yet implemented")
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