package com.a_23_59.nikeproject.data.repo.comment

import com.a_23_59.nikeproject.data.repo.comment.data_source.CommentDataSource
import io.reactivex.rxjava3.core.Single

class CommentRepositoryImpl(private val commentDataSource: CommentDataSource) : CommentRepository {

    override fun getAllComments(productId: Int): Single<List<Comment>> =
        commentDataSource.getAllComments(productId)


    override

    fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }
}