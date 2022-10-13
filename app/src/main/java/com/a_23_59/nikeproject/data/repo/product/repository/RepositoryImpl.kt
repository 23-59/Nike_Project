package com.a_23_59.nikeproject.data.repo.product.repository

import com.a_23_59.nikeproject.data.repo.product.Product
import com.a_23_59.nikeproject.data.repo.product.data_source.LocalSource
import com.a_23_59.nikeproject.data.repo.product.data_source.ProductDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(private val remoteSource: ProductDataSource
,private val localSource: LocalSource) : ProductRepository {

    override fun getProducts(sort:Int): Single<List<Product>>  {
       return remoteSource.getProducts(sort)
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