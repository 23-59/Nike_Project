package com.a_23_59.nikeproject.data.repo.comment.detail.data_source

import com.a_23_59.nikeproject.data.repo.comment.Comment
import com.a_23_59.nikeproject.services.http.ApiService
import io.reactivex.rxjava3.core.Single

class CommentDetailDataSourceImpl(private val apiService: ApiService): CommentDetailDataSource {

    override fun getAllComments(productId: Int): Single<List<Comment>> =apiService.getComments(productId)


    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }


}