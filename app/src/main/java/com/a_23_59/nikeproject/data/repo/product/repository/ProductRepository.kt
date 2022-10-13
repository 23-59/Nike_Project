package com.a_23_59.nikeproject.data.repo.product.repository

import com.a_23_59.nikeproject.data.repo.product.Product
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ProductRepository {

    fun getProducts(sort:Int): Single<List<Product>>

    fun getFavorites(): Single<List<Product>>

    fun deleteFromFavorites(): Completable

    fun addToFavorites(): Completable
}