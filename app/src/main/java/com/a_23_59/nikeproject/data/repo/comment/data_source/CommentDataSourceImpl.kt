package com.a_23_59.nikeproject.data.repo.comment.data_source

import com.a_23_59.nikeproject.data.repo.comment.Comment
import com.a_23_59.nikeproject.services.http.ApiService
import io.reactivex.rxjava3.core.Single

class CommentDataSourceImpl(private val apiService: ApiService): CommentDataSource {

    override fun getAllComments(productId:Int): Single<List<Comment>> = apiService.getComments(productId)

    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }
}