package com.a_23_59.nikeproject.data.repo.comment.detail

import com.a_23_59.nikeproject.data.repo.comment.Comment
import com.a_23_59.nikeproject.data.repo.comment.detail.data_source.CommentDetailDataSource
import io.reactivex.rxjava3.core.Single

class CommentDetailRepositoryImpl(
    private val commentDetailDataSource: CommentDetailDataSource
) : CommentDetailRepository {

    override fun getAllComments(productId: Int): Single<List<Comment>> =
        commentDetailDataSource.getAllComments(productId)


    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }
}